import dao.AutorDao;
import dao.CartiDao;
import dao.ClientiDao;
import model.Autor;
import model.Carti;
import model.Clienti;
import model.Genuri;

import java.util.Date;
import java.util.List;

public class BibliotecaMain {

    public static void main(String[] args) {
        AutorDao autorDao = new AutorDao();

        Autor autor = autorDao.findById(1);

        System.out.println(autor);

        Autor autor1 = new Autor();
        autor1.setNume("Danjo");
        autor1.setPrenume("Imbatabilul");
        //autorDao.save(autor1);

        List<Autor> autors = autorDao.getAll();
        System.out.println(autors);

        autorDao.deleteById(15);
        autorDao.save(null);

        // CARTI

        System.out.println("Cautare carte/id");

        CartiDao cartiDao = new CartiDao();
        Carti carti = cartiDao.findById(2);
        System.out.println(carti);

        System.out.println("Carte noua");

        Carti carti1=new Carti();
        carti1.setDenumire("La mare");
        carti1.setEditura("Nemira");
        carti1.setAutor(new Autor(19,"Mertin", "Ryzendwarf"));
        carti1.setGenuri(new Genuri(10,"Copii"));

        System.out.println(carti1);

        cartiDao.save(carti1);

        System.out.println("Toate cartile");

        List<Carti>books= cartiDao.getAll();
        System.out.println(books);

        cartiDao.deleteById(25);

        System.out.println("Clienti");

        System.out.println("Client find by id");


        ClientiDao clientiDao = new ClientiDao();
        Clienti clienti = clientiDao.findById(5);
        System.out.println(clienti);

        System.out.println("Client nou");

        Clienti clienti1 = new Clienti();
        clienti1.setNume("Dan");
        clienti1.setPrenume("Ardelean");
        clienti1.setTelefon("0742292281");
        clienti1.setEmail("danarde@hotmail.com");
        clienti1.setAdresa("Str.1 Aprilie, nr.69");
        //clientiDao.save(clienti1);
        System.out.println(clienti1);

        System.out.println("Toti clientii");

        List<Clienti> clientis = clientiDao.getAll();
        System.out.println(clientis);

        System.out.println("Tragem linie peste fraieri");

        clientiDao.deleteById(8);






    }


}
