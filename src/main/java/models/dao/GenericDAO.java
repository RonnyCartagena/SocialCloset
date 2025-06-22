package models.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import java.util.List;

public abstract class GenericDAO<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    private final Class <T> entityClass;

    // Constructor para inicializar la clase de la entidad
    protected GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
        // Si no tienes inyección de dependencias, inicializa el EntityManager manualmente
        if (this.entityManager == null) {
            this.entityManager = Persistence.createEntityManagerFactory("socialTrueque").createEntityManager();
        }
    }

    // Metodo para crear una entidad con manejo de transacciones
    public boolean create(T entity) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Couldn't create entity: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Metodo para actualizar una entidad con manejo de transacciones
    public boolean update(T entity) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Couldn't update entity: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Metodo para eliminar una entidad con manejo de transacciones
    public boolean remove(Object id) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            T entity = findById(id);
            if (entity != null) {
                entityManager.remove(entity);
                transaction.commit();
                return true;
            }
            transaction.rollback();
            return false;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Couldn't remove entity: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Método para encontrar una entidad por su ID
    public T findById(Object id) {
        try {
            return entityManager.find(entityClass, id);
        } catch (Exception e) {
            System.out.println("Couldn't find entity: " + e.getMessage());
            return null;
        }
    }

    // Metodo para encontrar todas las entidades de un tipo
    public List<T> findAll() {
        try {
            String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";
            return entityManager.createQuery(jpql, entityClass).getResultList();
        } catch (Exception e) {
            System.out.println("Couldn't retrieve entities: " + e.getMessage());
            return List.of();
        }
    }
}