package сom.work.example.myPackage.service;

import сom.work.example.myPackage.dao.ClientDao;
import сom.work.example.myPackage.dao.ContractDao;
import сom.work.example.myPackage.dao.InsuredPersonDao;
import сom.work.example.myPackage.model.Client;
import сom.work.example.myPackage.model.Contract;
import сom.work.example.myPackage.model.InsuredPerson;

import java.util.List;

public class ContractDBService {

    private final ContractDao contractDao;
    private final ClientDao clientDao;
    private final InsuredPersonDao insuredPersonDao;

    public ContractDBService(ContractDao contractDao, ClientDao clientDao, InsuredPersonDao insuredPersonDao) {
        this.contractDao = contractDao;
        this.clientDao = clientDao;
        this.insuredPersonDao = insuredPersonDao;
    }

    public void createContract(Contract contract) {
        insuredPersonDao.createAll(contract);
        clientDao.create(contract.getClient());
        contractDao.create(contract);
    }

    public Contract readContract(Contract contract) {
        Contract contract1 = contractDao.read(contract.getNumber());
        Client client = clientDao.read(contract.getClient().getINN());
        List<InsuredPerson> list = insuredPersonDao.readAll(contract);
        if (client != null){
            contract1.setClient(client);
        }
        if (!list.isEmpty()){
            contract1.setInsuredPeoples(list);
        }
        return contract1;
    }

    public void updateContract(Contract contract) {
        clientDao.update(contract.getClient());
        insuredPersonDao.updateAll(contract);
        contractDao.update(contract);
    }

    public void deleteContract(Contract contract) {
        contractDao.delete(contract.getNumber());
        clientDao.delete(contract.getClient().getINN());
        insuredPersonDao.deleteAll(contract);
    }
}
