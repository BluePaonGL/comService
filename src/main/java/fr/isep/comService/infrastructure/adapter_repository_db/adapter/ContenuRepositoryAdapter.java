package fr.isep.comService.infrastructure.adapter_repository_db.adapter;


import fr.isep.comService.domain.model.Contenu;
import fr.isep.comService.domain.port.ContenuRepositoryPort;
import fr.isep.comService.infrastructure.adapter_repository_db.DAO.ContenuDao;
import fr.isep.comService.infrastructure.adapter_repository_db.repository.ContenuRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ContenuRepositoryAdapter implements ContenuRepositoryPort {

    private final ContenuRepository contenuRepository;
    private final ModelMapper modelMapper;

    @Override
    public Contenu findById(String contenuId){
        ContenuDao contenuDao = this.contenuRepository.findByContenuId(contenuId);
        try{
            return this.modelMapper.map(contenuDao, Contenu.class);
        } catch (IllegalArgumentException exception){
            throw new IllegalArgumentException("This content does not exist in the database", exception);
        }
    }

    @Override
    public Contenu findByContenuType(String contenuType){
        ContenuDao contenuDao = this.contenuRepository.findByContenuType(contenuType);
        try{
            return this.modelMapper.map(contenuDao, Contenu.class);
        } catch (IllegalArgumentException exception){
            throw new IllegalArgumentException("This content type does not exist in the database", exception);
        }
    }

    @Override
    public Contenu saveContenu(Contenu contenu){
        ContenuDao contenuDao = this.modelMapper.map(contenu, ContenuDao.class);
        return this.modelMapper.map(this.contenuRepository.save(contenuDao), Contenu.class);
    }

    @Override
    public List<Contenu> findAll(){
        return this.contenuRepository.findAll()
                .stream().map(contenuDao -> this.modelMapper.map(contenuDao, Contenu.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String contenuId){
        ContenuDao contenuDao = this.contenuRepository.findByContenuId(contenuId);
        this.contenuRepository.delete(contenuDao);
    }
}
