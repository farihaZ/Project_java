
package cat.institutmvm.applicacio.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

import cat.institutmvm.applicacio.entities.Especies;

import static java.lang.Integer.*;

/**
 * Class formulario
 */
public class Formulario extends JFrame{
    private JPanel panelF;
    private JTextField habitat;
    private JTextField nubosidad;
    private JTextField viento;
    private JTextField direcion;
    private JTextField hora;
    private JTextField embarcaciones;
    private JTextField temp;
    private JTextField cebo;
    private JTextField sexo;
    private JTextField parasitos;
    private JTextField presenciaA;
    private JTextField numerosA;
    private JRadioButton si;
    private JRadioButton no;
    private JLabel especieLabel;
    private JTextField name;
    private JLabel profundidadLabel;
    private JTextField profundidad;
    private JLabel habitatLabel;
    private JLabel proSharksLabel;
    private JLabel tiempoDeNavigacionLabel;
    private JTextField tiempoDeNavegacion;
    private JLabel nubosidadLabel;
    private JLabel fuerzaDelVientoLabel;
    private JLabel direccionDelVientoLabel;
    private JLabel horaDeLlegadaALabel;
    private JLabel numeroDeEmbarcacionesLabel;
    private JLabel tempraturaSuperficialLabel;
    private JLabel tipoDeCeboLabel;
    private JLabel sexoLabel;
    private JLabel parásitosLabel;
    private JLabel presenciaDeAnzuelosLabel;
    private JLabel numerosDeAnzuelosLabel;
    private JButton buttonOk;
    private ArrayList<Especies> especie;

    /**
     * constructor
     */
    public Formulario(){
        super("Observaciones del Mar");
        this.setContentPane(this.panelF);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        especie = new ArrayList<Especies>();

        ActionListener alnew = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              Especies es = new Especies(
                      name.getText(),
                      habitat.getText(),
                      sexo.getText(),
                      profundidad.getText(),
                      tiempoDeNavegacion.getText(),
                      nubosidad.getText(),
                      viento.getText(),
                      direcion.getText(),
                      hora.getText(),
                      embarcaciones.getText(),
                      temp.getText(),
                      cebo.getText(),
                      presenciaA.getText(),
                      numerosA.getText(),
                      parasitos.getText(),
                      proSharksLabel.getText()
              );
              especie.add(es);
            }
        };
        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonOkClick(e);
            }
        });
    }

    /**
     * action of the button
     * @param e
     */
    public void buttonOkClick(ActionEvent e){
        Especies es = new Especies(
                name.getText(),
                habitat.getText(),
                sexo.getText(),
                profundidad.getText(),
                tiempoDeNavegacion.getText(),
                nubosidad.getText(),
                viento.getText(),
                direcion.getText(),
                hora.getText(),
                embarcaciones.getText(),
                temp.getText(),
                cebo.getText(),
                presenciaA.getText(),
                numerosA.getText(),
                parasitos.getText(),
                proSharksLabel.getText()
        );
        especie.add(es);
    }

    /**
     * add all the information introduced
     * @param e
     */
    public void addEspecies(Especies e){

        especie.add(e);

    }
    // add to Database
    public void addToDatabase() throws SQLException {
        Connection connection = null;
        PreparedStatement pstatement = null;
        ResultSet rs = null;
        String server = "jdbc:mysql-fariha.alwaysdata.net";
        String bbdd = "fariha_prog";
        String user = "fariha";
        String password = "x6968370e";
        String sql;

        // get the espeies info from gui
        String nombreE = name.getText();
        String habitatE = habitat.getText();
        String sexoE = sexo.getText();
        int profundidadE = profundidad.getComponentCount();
        int tiempoDeNavegacionE = tiempoDeNavegacion.getComponentCount();
        String nubosidadE = nubosidad.getText();
        String vientoE = viento.getText();
        String direcionE = direcion.getText();
        String horaE = hora.getText();
        int embarcacionesE = embarcaciones.getComponentCount();
        int tempE = temp.getComponentCount();
        String ceboE = cebo.getText();
        String presenciaAE = presenciaA.getText();
        int numeroAE = numerosA.getComponentCount();
        String parasitosE = parasitos.getText();
        boolean proSarkE = proSharksLabel.getAutoscrolls();


        try {
            // connect to database
            connection = DriverManager.getConnection(server + bbdd, user, password);
            // sql statement
            sql = "INSERT INTO usuaris (nombre , habitat,sexo,profundidad ,nubosidad,fuerzaViento,direcionViento,hora,cebo,parasitos,presenciaA,tiempoNavegacion,embaraciones,temperatura,numeroA,proSharks) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            try {

                // set params
                pstatement = connection.prepareStatement(sql);
                pstatement.setString(1, nombreE);
                pstatement.setString(2, habitatE);
                pstatement.setInt(3, profundidadE);
                pstatement.setBoolean(4, proSarkE);
                pstatement.setInt(5, tiempoDeNavegacionE);
                pstatement.setString(6, vientoE);
                pstatement.setString(7, horaE);
                pstatement.setInt(8, tempE);
                pstatement.setString(9, parasitosE);
                pstatement.setString(10, presenciaAE);
                pstatement.setInt(11, numeroAE);
                pstatement.setString(12, ceboE);
                pstatement.setString(13, sexoE);
                pstatement.setInt(14, embarcacionesE);
                pstatement.setString(15, nubosidadE);
                pstatement.setString(16, direcionE);

                //execute sql
                pstatement.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            // get maximum profundidad(sql )
            sql = "SELECT max(profundidad) FROM especies ;";

            pstatement = connection.prepareStatement(sql);
            rs = pstatement.executeQuery();

            while (rs.next()) {
                System.out.println(String.format("Profundidad Maxima : %d", rs.getInt(1)));
                System.out.println(String.format("----------------------------"));
            }

            // get minimum profundidad(sql )
            sql = "SELECT min(profundidad) FROM especies ;";

            pstatement = connection.prepareStatement(sql);
            rs = pstatement.executeQuery();

            while (rs.next()) {
                System.out.println(String.format("Profundidad Minima : %d", rs.getInt(1)));
                System.out.println(String.format("----------------------------"));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            rs.close();
            pstatement.close();
            connection.close();
        }

    }

    public static void main(String[] args) {
        Formulario formulario = new Formulario();
        formulario.setVisible(true);
    }

}

