package cl.SalmonesAustral.Alertas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.SalmonesAustral.Alertas.modelo.Alertas;

@Repository
public interface AlertaRepository extends JpaRepository<Alertas, Long> {

    // Buscar alertas por jaula
    List<Alertas> findByJaulaId(int jaulaId);

    // Buscar alertas por nivel
    List<Alertas> findByNivel(String nivel);

    // Buscar alertas por estado
    List<Alertas> findByEstado(String estado);
}
