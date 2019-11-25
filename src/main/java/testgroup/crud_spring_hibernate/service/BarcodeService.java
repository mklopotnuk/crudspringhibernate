package testgroup.crud_spring_hibernate.service;

import testgroup.crud_spring_hibernate.model.Barcode;

import java.util.List;

public interface BarcodeService {

    List<Barcode> allBarcodes();

    void add(Barcode barcode);

    void delete(Barcode barcode);

    void edit(Barcode barcode);

    Barcode getById(Long id);
}
