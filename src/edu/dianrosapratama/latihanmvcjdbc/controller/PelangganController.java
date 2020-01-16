/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dianrosapratama.latihanmvcjdbc.controller;

import edu.dianrosapratama.latihanmvcjdbc.model.PelangganModel;
import edu.dianrosapratama.latihanmvcjdbc.view.pelanngganview;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class PelangganController {

    private PelangganModel model;

    public void setModel(PelangganModel model) {
        this.model = model;
    }

    public void resetPelanggan(pelanngganview view) {
        model.resetPelanggan();
    }

    public void insertPelanggan(pelanngganview view) {
        String nama = view.getTbNama().getText();
        String alamat = view.getTbAlamat().getText();
        String telepon = view.getTbTelepon().getText();
        String email = view.getTbEmail().getText();

        if (nama.trim().equals("")) {
            JOptionPane.showMessageDialog(view, "Nama Tidak Boleh Kosong");
        } else if (nama.length() > 255) {
            JOptionPane.showMessageDialog(view, "Nama tidak boleh lebih dari 255 karakter");
        } else if (telepon.length() > 12) {
            JOptionPane.showMessageDialog(view, "Nomor telepon tidak boleh lebih dari 12 digit");
        } else if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(view, "Email tidak valid");
        } else {
            model.setNama(nama);
            model.setAlamat(alamat);
            model.setTelepon(telepon);
            model.setEmail(email);

            try {
                model.insertPelanggan();
                JOptionPane.showMessageDialog(view, "Pelanggan berhasil di tambahkan");
                model.resetPelanggan();
            } catch (Throwable throwable) {
                JOptionPane.showMessageDialog(view, new Object[]{"Terjadi Error, degan pesan ", throwable.getMessage()});
            }
        }
    }

    public void updatePelanggan(pelanngganview view) {
        if (view.getTableData().getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(view, "Silahkan seleksi barisa data yang akan di ubah");
            return;
        }

        Integer id = Integer.parseInt(view.getTbId().getText());
        String nama = view.getTbNama().getText();
        String alamat = view.getTbAlamat().getText();
        String telepon = view.getTbTelepon().getText();
        String email = view.getTbEmail().getText();

        if (nama.trim().equals("")) {
            JOptionPane.showMessageDialog(view, "Nama Tidak Boleh Kosong");
        } else if (nama.length() > 255) {
            JOptionPane.showMessageDialog(view, "Nama tidak boleh lebih dari 255 karakter");
        } else if (telepon.length() > 12) {
            JOptionPane.showMessageDialog(view, "Nomor telepon tidak boleh lebih dari 12 digit");
        } else if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(view, "Email tidak valid");
        } else {
            model.setId(id);
            model.setNama(nama);
            model.setAlamat(alamat);
            model.setTelepon(telepon);
            model.setEmail(email);

            try {
                model.updatePelanggan();
                JOptionPane.showMessageDialog(view, "Pelanggan berhasil di ubah");
                model.resetPelanggan();
            } catch (Throwable throwable) {
                JOptionPane.showMessageDialog(view, new Object[]{"Terjadi Error, degan pesan ", throwable.getMessage()});
            }
        }
    }

    public void deletePelanggan(pelanngganview view) {
        if (view.getTableData().getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(view, "Silahkan seleksi barisa data yang akan di hapus");
            return;
        }

        if (JOptionPane.showConfirmDialog(view, "Anda yakin akan menghapus? ") == JOptionPane.OK_OPTION) {
            Integer id = Integer.parseInt(view.getTbId().getText());
            model.setId(id);
            try {
                model.deletePelanggan();
                JOptionPane.showMessageDialog(view, "Pelanggan berhasil di hapus");
                model.resetPelanggan();
            } catch (Throwable throwable) {
                JOptionPane.showMessageDialog(view, new Object[]{"Terjadi Error, degan pesan ", throwable.getMessage()});
            }
        }
    }
}
