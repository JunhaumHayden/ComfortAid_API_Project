package edu.ifsc.fln.confortaid.service;

import edu.ifsc.fln.confortaid.exception.HorarioOcupadoException;
import edu.ifsc.fln.confortaid.model.Agendamento;
import edu.ifsc.fln.confortaid.model.Profissional;
import edu.ifsc.fln.confortaid.repository.AgendamentoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class AgendamentoService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AgendamentoRepository agendamentoRepository;


    public Agendamento agendar(Agendamento agendamento) {
        validarHorario(agendamento.getDataHora());

        if (verificarHorarioOcupado(agendamento)) {
            throw new HorarioOcupadoException("Horário já ocupado pelo profissional.");
        }
        agendamento.setStatus(Agendamento.Status.PENDENTE);
        return agendamentoRepository.save(agendamento);
    }

    public Agendamento reagendar(Agendamento agendamento) {
        validarStatusParaReagendamento(agendamento);
        validarHorario(agendamento.getDataHora());

        if (verificarHorarioOcupado(agendamento)) {
            throw new HorarioOcupadoException("Horário já ocupado pelo profissional.");
        }
        agendamento.setStatus(Agendamento.Status.PENDENTE);
        return agendamentoRepository.save(agendamento);
    }

    public void alterarStatus(Agendamento agendamento, Agendamento.Status novoStatus) {
        validarStatusParaReagendamento(agendamento);
        agendamento.setStatus(novoStatus);
        agendamentoRepository.save(agendamento);
    }

    private boolean verificarHorarioOcupado(Agendamento agendamento) {
        Profissional profissional = agendamento.getServico().getProfissional();
        LocalDateTime horario = agendamento.getDataHora();
        List<Agendamento> agendamentos = entityManager.createQuery(
                        "SELECT a FROM Agendamento a WHERE a.servico.profissional = :profissional AND a.dataHora = :horario", Agendamento.class)
                .setParameter("profissional", profissional)
                .setParameter("horario", horario)
                .getResultList();

        return !agendamentos.isEmpty();
    }

    private void validarHorario(LocalDateTime horarioDateTime) {
        if (horarioDateTime.isBefore(LocalDateTime.now())) {
            throw new HorarioOcupadoException("Não é possível agendar para uma data que já passou.");
        }


        int hora = horarioDateTime.getHour();
        if (hora < 8 || hora > 20) {
            throw new HorarioOcupadoException("Horário fora do período de atendimento.");
        }
    }

    private void validarStatusParaReagendamento(Agendamento agendamento) {
        if (agendamento == null) {
            throw new HorarioOcupadoException("O objeto Agendamento não pode ser nulo.");
        }

        if (Objects.equals(agendamento.getStatus(), Agendamento.Status.CANCELADO) || Objects.equals(agendamento.getStatus(), Agendamento.Status.ATENDIDO)) {
            throw new HorarioOcupadoException("Não é possível modificar um agendamento cancelado ou atendido.");
        }
    }
}



