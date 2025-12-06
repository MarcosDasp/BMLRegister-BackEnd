package com.bmlregister.formularios.repository.procedures;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.bmlregister.formularios.entities.Processo;

public interface AtualizarProcessoRepository extends JpaRepository<Processo, Integer>  {
        
        @Procedure(procedureName = "pc_AtualizarProcesso")
        void atualizarProcesso( @Param ("nomeEmpresa") String nomeEmpresa, 
                                @Param ("telefoneEmpresa") String telefoneEmpresa,
                                @Param ("cnpj") String cnpj, 
                                @Param ("valor") Float valor, 
                                @Param ("observacao") String observacao,
                                @Param ("prazo") int prazo, 
                                @Param ("id") int id
                              );
}
