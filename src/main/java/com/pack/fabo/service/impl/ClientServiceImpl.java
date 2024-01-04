package com.pack.fabo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pack.fabo.entity.Client;
import com.pack.fabo.repository.ClientRepository;
import com.pack.fabo.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService{

	private ClientRepository clientRepository;
	
	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}

	@Override
	public Client saveClient(Client client) {
		return clientRepository.save(client);
	}

	@Override
	public Client getClientById(Long id) {
		return clientRepository.findById(id).get();
	}

	@Override
	public Client updateClient(Client client) {
		return clientRepository.save(client);
	}

	@Override
	public void deleteClientById(Long id) {
		clientRepository.deleteById(id);	
	}
	
    
    public List<Client> getDistinctStatesAndCities() {
        List<Client> allLocations = clientRepository.findAll();

        return allLocations.stream()
            .map(location -> {
                location.setState(location.getState().toLowerCase().trim());
                location.setCity(location.getCity().toLowerCase().trim());
                return location;
            })
            .distinct()
            .collect(Collectors.toList());
    }

	@Override
	public Optional<Client> getClientByEmail(String email) {
		return clientRepository.findByEmail(email);
	}

    
    
}
