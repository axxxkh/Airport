package Airport.Logic;

import Airport.DAO.GenericDAO;
import Airport.DAO.Impl.AircraftDAOImpl;
import Airport.DAO.Impl.AvialineDAOImpl;
import Airport.Entity.Aircraft;
import Airport.Entity.Avialine;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        GenericDAO<Aircraft> aircraftDAO = new AircraftDAOImpl();
//        GenericDAO<Aircraft> aircraftGen = new DAOImpl<>();
//        GenericDAO<Aircraft> listDAO = new DAOImpl<>();
//        List<Aircraft> lisss = listDAO.getAll();
//        lisss.stream().forEach(System.out::println);
        GenericDAO<Avialine> avialine = new AvialineDAOImpl();
//        System.out.println(avialine.getById(1));
        List<Aircraft> list = aircraftDAO.getAll();
        System.out.println(avialine.getById(1));
        list.stream().forEach(System.out::println);
    }
}
