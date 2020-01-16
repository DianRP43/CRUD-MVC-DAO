/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dianrosapratama.latihanmvcjdbc.impl;

import edu.dianrosapratama.latihanmvcjdbc.entity.Pelanggan;
import edu.dianrosapratama.latihanmvcjdbc.error.PelangganException;
import edu.dianrosapratama.latihanmvcjdbc.service.PelangganDAO;
import java.sql.Connection;

import java.sql.SQLException;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class PelangganDaoImpl implements PelangganDAO {

    private Connection conn;
    private final String insertPelanggan = "INSERT INTO PELANGGAN(NAMA,ALAMAT,TELEPON,EMAIL) VALUES(?,?,?,?)";
    private final String updatePelanggan = "UPDATE PELANGGAN SET NAMA=?,ALAMAT=?,TELEPON=?,EMAIL=? WHERE ID=? ";
    private final String deletePelanggan = "DELETE FROM PELANGGAN WHERE ID=?";
    private final String getById = "SELECT * FROM PELANGGAN WHERE ID=?";
    private final String getByEmail = "SELECT * FROM PELANGGAN WHERE EMAIL=?";
    private final String selectAll = "SELECT * FROM PELANGGAN";

    public PelangganDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertPelanggan(Pelanggan pelanggan) throws PelangganException {
        PreparedStatement statement = null;
        try {
            conn.setAutoCommit(false);
            statement = conn.prepareStatement(insertPelanggan, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, pelanggan.getNama());
            statement.setString(2, pelanggan.getAlamat());
            statement.setString(3, pelanggan.getTelepon());
            statement.setString(4, pelanggan.getEmail());
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                pelanggan.setId(result.getInt(1));
            } 
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
            }
            throw new PelangganException(e.getMessage());
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException ex) {

            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {

                }
            }
        }
    }

    @Override
    public void updatePelanggan(Pelanggan pelanggan) throws PelangganException {
        PreparedStatement statement = null;
        try {
            conn.setAutoCommit(false);
            statement = conn.prepareStatement(updatePelanggan);
            statement.setString(1, pelanggan.getNama());
            statement.setString(2, pelanggan.getAlamat());
            statement.setString(3, pelanggan.getTelepon());
            statement.setString(4, pelanggan.getEmail());
            statement.setInt(5, pelanggan.getId());
            statement.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
            }
            throw new PelangganException(e.getMessage());
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    @Override
    public void deletePelanggan(Integer id) throws PelangganException {
        PreparedStatement statement = null;
        try {
            conn.setAutoCommit(false);
            statement = conn.prepareStatement(deletePelanggan);
            statement.setInt(1, id);
            statement.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
            }
            throw new PelangganException(e.getMessage());
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    @Override
    public Pelanggan getPelanggan(Integer id) throws PelangganException {
        PreparedStatement statement = null;
        try {
            conn.setAutoCommit(false);
            statement = conn.prepareStatement(getById);
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();
            Pelanggan pelanggan = null;
            if (result.next()) {
                pelanggan = new Pelanggan();
                pelanggan.setId(result.getInt("ID"));
                pelanggan.setNama(result.getString("NAMA"));
                pelanggan.setAlamat(result.getString("ALAMAT"));
                pelanggan.setTelepon(result.getString("TELEPON"));
                pelanggan.setEmail(result.getString("EMAIL"));
            } else {
                throw new PelangganException("PELANGGAN DENGAN ID " + id + "TIDAK DITEMUKAN");
            }
            conn.commit();
            return pelanggan;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
            }
            throw new PelangganException(e.getMessage());
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    @Override
    public Pelanggan getPelanggan(String email) throws PelangganException {
        PreparedStatement statement = null;
        try {
            conn.setAutoCommit(false);
            statement = conn.prepareStatement(getByEmail);
            statement.setString(1, email);

            ResultSet result = statement.executeQuery();
            Pelanggan pelanggan = null;
            if (result.next()) {
                pelanggan = new Pelanggan();
                pelanggan.setId(result.getInt("ID"));
                pelanggan.setNama(result.getString("NAMA"));
                pelanggan.setAlamat(result.getString("ALAMAT"));
                pelanggan.setTelepon(result.getString("TELEPON"));
                pelanggan.setEmail(result.getString("EMAIL"));
            } else {
                throw new PelangganException("PELANGGAN DENGAN EMAIL " + email + "TIDAK DITEMUKAN");
            }
            conn.commit();
            return pelanggan;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
            }
            throw new PelangganException(e.getMessage());
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    @Override
    public List<Pelanggan> selectAllPelanggan() throws PelangganException {
        Statement statement = null;
        List<Pelanggan> list = new ArrayList<Pelanggan>();

        try {
            conn.setAutoCommit(false);
            statement = conn.createStatement();
            ResultSet result = statement.executeQuery(selectAll);
            Pelanggan pelanggan = null;
            while (result.next()) {
                pelanggan = new Pelanggan();
                pelanggan.setId(result.getInt("ID"));
                pelanggan.setNama(result.getString("NAMA"));
                pelanggan.setAlamat(result.getString("ALAMAT"));
                pelanggan.setTelepon(result.getString("TELEPON"));
                pelanggan.setEmail(result.getString("EMAIL"));
                list.add(pelanggan);
            }
            conn.commit();
            return list;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
            }
            throw new PelangganException(e.getMessage());
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (Exception e) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
        }
    }

}
