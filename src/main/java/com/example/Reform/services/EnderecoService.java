package com.example.Reform.services;

import com.example.Reform.entities.Endereco;
import com.example.Reform.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    public List<Endereco> findAllEnderecos(){
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> findEnderecoById(Long id){
        return enderecoRepository.findById(id);
    }

    public Endereco saveEndereco(Endereco endereco){
        return enderecoRepository.save(endereco);
    }

    public void deleteEndereco(Long id){
        enderecoRepository.deleteById(id);
    }

    public Endereco updateEndereco(Long id, Endereco endereco){
        Optional<Endereco> enderecoFind = enderecoRepository.findById(id);

        if(enderecoFind.isPresent()){
            Endereco enderecoUpdate = enderecoFind.get();

            enderecoUpdate.setLogradouro(endereco.getLogradouro());
            enderecoUpdate.setNumero(endereco.getNumero());
            enderecoUpdate.setComplemento(endereco.getComplemento());
            enderecoUpdate.setCep(endereco.getCep());
            enderecoUpdate.setBairro(endereco.getBairro());
            enderecoUpdate.setMunicipio(endereco.getMunicipio());
            enderecoUpdate.setUf(endereco.getUf());

            return enderecoRepository.save(enderecoUpdate);
        }else{
           throw new RuntimeException("Endereco não encontrado com ID: "+id);
        }
    }
}
