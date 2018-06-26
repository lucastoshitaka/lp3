package DAOs;
import Entidades.Modalidades;
import java.util.ArrayList;
import java.util.List;

public class DAOModalidades extends DAOGenerico<Modalidades> {

    public DAOModalidades() {
        super(Modalidades.class);
    }

    public int autoIdModalidades() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idModalidades) FROM Modalidades e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Modalidades> listByNome(String nomeModalidades) {
        return em.createQuery("SELECT e FROM Modalidades e WHERE e.nomeModalidades LIKE :nomeModalidades").setParameter("nomeModalidades", "%" + nomeModalidades + "%").getResultList();
    }

    public List<Modalidades> listById(int id) {
        return em.createQuery("SELECT e FROM Modalidades e WHERE e.idModalidades = :id").setParameter("id", id).getResultList();
    }

    public List<Modalidades> listInOrderNome() {
        return em.createQuery("SELECT e FROM Modalidades e ORDER BY e.nomeModalidades").getResultList();
    }

    public List<Modalidades> listInOrderId() {
        return em.createQuery("SELECT e FROM Modalidades e ORDER BY e.idModalidades").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Modalidades> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdModalidades()
 + "-" + lf.get(i).getNomeModalidades()
);
        }
        return ls;
    }
}
