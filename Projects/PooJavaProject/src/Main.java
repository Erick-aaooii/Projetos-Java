import java.time.LocalDate;

import br.com.dio.desafio.dominio.Bootcamp;
import br.com.dio.desafio.dominio.Curso;
import br.com.dio.desafio.dominio.Dev;
import br.com.dio.desafio.dominio.Mentoria;

public class Main {
    public static void main(String[] args) {
        //Criação do curso
        Curso curso1 = new Curso();
        curso1.setTitulo("Curso java");
        curso1.setDescricao("Descrição do curso java");
        curso1.setCargaHoraria(8);
        //Criação do segundo curso
        Curso curso2 = new Curso();
        curso1.setTitulo("Curso Js");
        curso1.setDescricao("Descrição do curso Js");
        curso1.setCargaHoraria(4);

        Mentoria mentoria1 = new Mentoria();
        mentoria1.setTitulo("Mentoria java");
        mentoria1.setDescricao("descrição Mentoria java");
        mentoria1.setData(LocalDate.now());

        Bootcamp bootcamp = new Bootcamp();
        bootcamp.setNome("Bootcamp Java Developer");
        bootcamp.setDescricao("Descrição Bootcamp Java Developer");
        bootcamp.getConteudos().add(curso1);
        bootcamp.getConteudos().add(curso2);
        bootcamp.getConteudos().add(mentoria1);

        Dev devErick = new Dev();
        devErick.setNome("Erick");
        devErick.inscreverBootcamp(bootcamp);
        System.out.println("Conteúdos Inscritos Erick:" + devErick.getConteudosInscritos());
        devErick.progredir();
        

        Dev devJoao = new Dev();
        devJoao.setNome("joão");
        devJoao.inscreverBootcamp(bootcamp);
        System.out.println("Conteúdos Inscritos Joao:" + devJoao.getConteudosInscritos());
        devJoao.progredir();

        //Concluidos 
        System.out.println("Conteúdos concluidos Erick:" + devErick.getConteudosConcluidos());
        System.out.println("XP:" + devErick.calcularTotalXp());
        System.out.println("Conteúdos concluidos Joao:" + devJoao .getConteudosConcluidos());
          
        
    }
}
