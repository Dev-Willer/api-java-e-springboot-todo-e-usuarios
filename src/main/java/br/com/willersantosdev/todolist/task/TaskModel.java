package br.com.willersantosdev.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity(name = "tb_tasks")
public class TaskModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID Id;

    @Column(length = 50)
    private String titulo;
    private String descricao;
    private LocalDateTime data_inicio;
    private LocalDateTime data_termino;
    private String prioridade;

    private UUID idUser;

    @CreationTimestamp
    private LocalDateTime criacao; 

    public void setTitulo(String titulo) throws Exception {
        if (titulo.length() > 50) {
            throw new Exception("O Campo titulo deve conter no max. 50 caracteres.");
        }
        this.titulo = titulo;
    }

}
