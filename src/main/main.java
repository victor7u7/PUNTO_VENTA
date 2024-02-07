/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;
import javax.swing.UIManager;
import punto_venta.menuAdmin;
import punto_venta.ventanaLogin;
/**
 *
 * @author israe
 */
public class main {
public static void main(String[] args) {
            FlatMaterialLighterIJTheme.setup();
            // Crear e hacer visible la ventana
//            ventanaLogin VL = new ventanaLogin();
//            VL.setVisible(true);
            

            
            
              

            menuAdmin MA=new menuAdmin("JUANITO EL MAS CAPITO");
            MA.setVisible(true);
            
        
    }
}
