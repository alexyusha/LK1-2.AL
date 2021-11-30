package сom.work.example.myPackage.controller;

import сom.work.example.myPackage.dao.ClientDao;
import сom.work.example.myPackage.dao.ContractDao;
import сom.work.example.myPackage.dao.InsuredPersonDao;
import сom.work.example.myPackage.model.Client;
import сom.work.example.myPackage.model.Contract;
import сom.work.example.myPackage.model.InsuredPerson;

import java.util.List;

public class ContractController {

    private final ContractDao contractDao;
    private final ClientDao clientDao;
    private final InsuredPersonDao insuredPersonDao;

    public ContractController(ContractDao contractDao, ClientDao clientDao, InsuredPersonDao insuredPersonDao) {
        this.contractDao = contractDao;
        this.clientDao = clientDao;
        this.insuredPersonDao = insuredPersonDao;
    }

    public void createContract(Contract contract){
        clientDao.setNumberContract(contract.getNumber());
        insuredPersonDao.setNumberContract(contract.getNumber());

        contractDao.create(contract);
        clientDao.create(contract.getClient());
        insuredPersonDao.createAll(contract.getInsuredPeoples());
    }

    public Contract readContract(int number){
        clientDao.setNumberContract(number);
        insuredPersonDao.setNumberContract(number);

        Contract contract = contractDao.read(number);
        Client client = clientDao.read(number);
        List<InsuredPerson> list = insuredPersonDao.readAll(number);
        contract.setClient(client);
        contract.setInsuredPeoples(list);
        return contract;
    }

    public void deleteContract(int number){
        clientDao.setNumberContract(number);
        insuredPersonDao.setNumberContract(number);

        contractDao.delete(number);
        clientDao.delete(number);
        insuredPersonDao.deleteAll(number);
    }
}
