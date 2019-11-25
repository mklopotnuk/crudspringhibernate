package testgroup.crud_spring_hibernate.dao;

import testgroup.crud_spring_hibernate.model.Barcode;

import java.util.List;

public interface BarcodeDAO {
    List<Barcode> allBarcodes();

    Long add(Barcode barcode);

    void delete(Long id);

    void edit(Barcode barcode);

    Barcode getById(Long id);
}
