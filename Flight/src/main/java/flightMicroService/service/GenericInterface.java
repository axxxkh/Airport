package flightMicroService.service;

public interface GenericInterface<T> {
    //    crud
    void delete(T entityDTO);

    void add(T entityDTO);
}
