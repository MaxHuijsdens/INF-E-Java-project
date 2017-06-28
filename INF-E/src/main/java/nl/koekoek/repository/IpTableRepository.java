package nl.koekoek.repository;

import nl.koekoek.model.Iptable;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Bastiaan on 11-8-2014.
 */
public interface IpTableRepository extends JpaRepository<Iptable, Long> {

    /**
     * finds a iptable by.
     * @param tableRule name of the tablerule
     * @return found iptable
     */
    Iptable findByTableRule(String tableRule);

}
