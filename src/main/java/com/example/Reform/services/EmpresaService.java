package com.example.Reform.services;

import com.example.Reform.Form.CreateEmpresaForm;
import com.example.Reform.entities.Empresa;
import com.example.Reform.entities.Endereco;
import com.example.Reform.repositories.EmpresaRepository;
import com.example.Reform.repositories.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Empresa> findAllEmpresas(){
        return empresaRepository.findAll();
    }

    public Optional<Empresa> findEmpresaById(Long id){
        return empresaRepository.findById(id);
    }

    @Transactional
    public Empresa createEmpresa(CreateEmpresaForm empresaForm) {
        // Verifica se CNPJ já existe
        Optional<Empresa> empresaExistente = empresaRepository.findByCnpj(empresaForm.getCnpj());
        if (empresaExistente.isPresent()) {
            throw new RuntimeException("CNPJ já está em uso.");
        }

        // Codifica a senha
        empresaForm.setSenha(passwordEncoder.encode(empresaForm.getSenha()));

        // Cria a empresa
        Empresa empresa = new Empresa();
        empresa.setNome(empresaForm.getNome());
        empresa.setEmail(empresaForm.getEmail());
        empresa.setCnpj(empresaForm.getCnpj());
        empresa.setTelefone(empresaForm.getTelefone());
        empresa.setSenha(empresaForm.getSenha());
        empresa.setDescricao(empresaForm.getDescricao());

        // Cria o endereço principal
        Endereco endereco = new Endereco();
        endereco.setLogradouro(empresaForm.getLogradouro());
        endereco.setNumero(empresaForm.getNumero());
        endereco.setComplemento(empresaForm.getComplemento());
        endereco.setCep(empresaForm.getCep());
        endereco.setBairro(empresaForm.getBairro());
        endereco.setMunicipio(empresaForm.getMunicipio());
        endereco.setUf(empresaForm.getUf());
        endereco.setMain(true);

        Empresa empresaSalva = empresaRepository.save(empresa);

        endereco.setEmpresa(empresaSalva); // vínculo bidirecional

        enderecoRepository.save(endereco);

        // Salva a empresa (com cascade, o endereço também será salvo)
        return empresaRepository.save(empresa);
    }

    @Transactional
    public Empresa updateEmpresa(Long id, Empresa empresa){
        Optional<Empresa> empresaFind = empresaRepository.findById(id);

        if(empresaFind.isPresent()){
            Empresa empresaUpdate = empresaFind.get();
            empresaUpdate.setNome(empresa.getNome());
            empresaUpdate.setEmail(empresa.getEmail());
            empresaUpdate.setCnpj(empresa.getCnpj());
            empresaUpdate.setTelefone(empresa.getTelefone());
            empresaUpdate.setSenha(empresa.getSenha());
            empresaUpdate.setDescricao(empresa.getDescricao());

            return empresaRepository.save(empresaUpdate);

        }else {
            throw new RuntimeException("Empresa não encontrada com ID:"+id);
        }
    }

    public void deleteEmpresaById(Long id){
        empresaRepository.deleteById(id);
    }

    public Optional<Empresa> findEmpresaByCnpj(String cnpj){
        return empresaRepository.findByCnpj(cnpj);
    }

}
