package fitness_tracker;

import java.awt.Color;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wgome
 */

public class Menu_Form extends javax.swing.JFrame {
    
    
    
    
    login_screen f = new login_screen();
    getusername m = new getusername();
    String username;


    /**
     * Creates new form Menu_form
     */
    public Menu_Form() {
        
      
        
        initComponents();
        this.heightUnit = "CM";
        this.weightUnit = "KG";
        this.gender = "MALE";
        this.age = 0;
        this.height = 0;
        this.weight = 0;
        textHeightFeet.hide();
        textHeightInches.hide();
        textHeightFeet.setText("0");
        textHeightInches.setText("0");
        textWeightLB.hide();
        textWeightLB.setText("0");
        textWeightSt.setText("0");
        textWeightSt.hide();
        addchoiceitem();
        



    }
    
    public Menu_Form(String user){
        initComponents();
        userid.setText(user);
        username=user;
     
    }
    

    
    double calculatescore()
    {
    int Intensity;
    if(intcombo.getSelectedIndex()==0)
    { Intensity=5;
    }
    else if(intcombo.getSelectedIndex()==1)
    {
        Intensity=10;
    }
    else
    {
        Intensity=15;
    }
    
    Float time=Float.parseFloat(timetb.getText());
    Float calories=Float.parseFloat(caloriestb.getText());
    Float scale=Float.parseFloat(scaletb.getText());
    Float score = Intensity + time + calories/10 + scale;
    opscore.setText("Congrats you've earned " +score +"  myFit points");
    return score;
    
   }
   
    /**.
     * Creates new form BMI

    /**.
     * age getter
     * @return int: Age
     */
   
    
    void addchoiceitem()
    { choice1.add("Daily");
      choice1.add("Monthly");
    }
    public int getAge() {
        return age;
    }
    public void setAge(final int age) {
        this.age = age;
    }
    /**.
     * Height Getter
     * @return double: height
     */
    public double getHeight_() {
        return height;
    }
    /**.
     * height setter
     * @param height double: height of the person
     */
    public void setHeight(final double height) {
        this.height = height;
    }
    /**.
     * weight getter
     * @return double: weight of the person
     */
    public double getWeight() {
        return weight;
    }
    /**.
     * weight setter
     * @param weight double: weight of the person 
     */
    public void setWeight(final double weight) {
        this.weight = weight;
    }
    /**.
     * gender getter
     * @return String: Male or Female
     */
    public String getGender() {
        return gender;
    }
    /**.
     * gender setter
     * @param gender String : Male or Female 
     */
    public void setGender(final String gender) {
        this.gender = gender;
    }
    /**.
     * weight unit getter
     * @return String: KG or LB or LB+ST
     */
    public String getWeightUnit() {
        return weightUnit;
    }
    /**.
     * setter for weight unit 
     * @param weightUnit String : KG or LB or LB+ST
     */
    public void setWeightUnit(final String weightUnit) {
        this.weightUnit = weightUnit;
    }
    /**.
     * getter for height unit
     * @return String CM or FT+IN
     */
    public String getHeightUnit() {
        return heightUnit;
    }
    /**.
     * setter for the height unit
     * @param heightUnit String: CM or FT+IN
     */
    public void setHeightUnit(final String heightUnit) {
        this.heightUnit = heightUnit;
    }
    /**.
     * getter for BMI Value
     * @return double: Body Mass Index
     */
    public double getBmi() {
        return bmi;
    }
    /**.
     * setter for BMI value
     * @param bmi double: BOdy Mass Index
     */
    public void setBmi(final double bmi) {
        this.bmi = bmi;
    }
    /**.
     * getter for ideal Weight
     * @return Double: Ideal Weight
     */
    public double getIdealWeight() {
        return idealWeight;
    }
    /**.
     * Setter for idea weight
     * @param idealWeight Double Ideal Weight
     */
    public void setIdealWeight(final double idealWeight) {
        this.idealWeight = idealWeight;
    }
    /**.
     * getter for FAT value
     * @return Double: FAT Value
     */
    public double getFat() {
        return fat;
    }
    /**.
     * setter for the fat value
     * @param fat Double FAT Value
     */
    public void setFat(double fat) {
        this.fat = fat;
    }
    /**
     * Calculates BMI Value and Displays it to the BMI Value Label
     */
    private void calculateBMI() {
        double h = this.getHeight_() / 100;
        this.setBmi(this.getWeight() / (h * h));
        String bmiLblTxt = "" + getBmi() + "";
        lblBMIValue.setText(bmiLblTxt.substring(0, 5));
        showMessage();
    }
    /**
     * calculates Ideal Weight
     * @return Double : the Ideal weight of the person
     */
    private double calculateIdealWeight() {
        if (this.height != 0) {
            double inchesHeight;
            double finalIdealWeightKG = 0;
            if ("CM".equals(this.heightUnit)) {
                inchesHeight = this.height * 0.393701;
            }
            else {
                inchesHeight = this.height * 0.393701;
            }
            double inchesOver5Feet = inchesHeight - 60.0;
            if (this.gender.equals("FEMALE")) {
                finalIdealWeightKG = 53.1 + (1.36 * inchesOver5Feet);
                this.idealWeight = finalIdealWeightKG;
            } else if (!this.gender.equals("MALE")) {
                if (this.weightUnit.equals("KG")) {
                    lblIdealWeightValue.setText("0.0KG");
                } else {
                    lblIdealWeightValue.setText("0.0LBS");
                    return 0.0;
                }
            } else {
                finalIdealWeightKG = 56.2 + (1.41 * inchesOver5Feet);
                this.idealWeight = finalIdealWeightKG;
            }
            if (finalIdealWeightKG >= 200.0) {
                if (this.weightUnit.equals("KG")) {
                    lblIdealWeightValue.setText("0.0" + "KG");
                } else {
                    lblIdealWeightValue.setText("0.0" + "LBS");
                    return 0.0;
                }
            }
            if ("KG".equals(this.weightUnit)) {
                String temp = "" + this.getIdealWeight();
                lblIdealWeightValue.setText(temp.substring(0, 6) + "KG");
                return finalIdealWeightKG;
            } else if (!this.weightUnit.equals("LB+ST") && !this.weightUnit.equals("LB")) {
                lblIdealWeightValue.setText("0.0");
                return 0.0;
            } else {
                String temp = "" + this.getIdealWeight() * 2.20462;
                lblIdealWeightValue.setText(temp.substring(0,6) + "LBS");
                return finalIdealWeightKG * 2.20462;
            }
        }
        return 0;
    }
    /**
     * Calculates FAT
     * @return Double: FAT
     */
    private double calculateFAT() {
        int i = 1;
        if (this.bmi > 100 || this.age < 1) {
            this.lblFATValue.setText("0.0 %");
            return 0;
        }
        double f = (1.2 * this.bmi) + (0.23 * this.age);
        if (!this.gender.equals("MALE")) {
            i = 0;
        }
        this.fat = (f - (((float) i) * 10.8)) - 5.4;
        if (this.fat < 100.0f && this.fat >= 0 ) {
            String temp = "" + this.fat;
            this.lblFATValue.setText("" + temp.substring(0, 5) + "%");
            return this.fat;
        }
        lblFATValue.setText("0%");
        return 0;
    }
    /**
     * Displays a message after BMI Calculation on the
     * basis of BMI Value and changes the message colour accordingly
     */
    private void showMessage(){
        if (this.bmi < 16) {
            lblMessage.setText("You need to eat more");
            lblMessage.setForeground(Color.red);
        } else if (bmi >= 16 && bmi < 17   ) {
            lblMessage.setText("You need to eat more");
            lblMessage.setForeground(Color.pink);
        } else if (bmi >= 17 && bmi < 18.5) {
            lblMessage.setText("Time for excercise..");
            lblMessage.setForeground(Color.yellow);
        } else if (bmi >= 18.5 && bmi < 25) {
            lblMessage.setText("Wow! you've got great fit");
            lblMessage.setForeground(Color.black);
        } else if (bmi >= 25 && bmi < 30) {
            lblMessage.setText("Time for a run ..");
            lblMessage.setForeground(Color.yellow);
        } else if (bmi >= 30 && bmi < 35) {
            lblMessage.setText("Time for a run..");
            lblMessage.setForeground(Color.red);
        } else if (bmi >= 40 ) {
            lblMessage.setText("Are you human??");
            lblMessage.setForeground(Color.black);
        }
    }
 private int age;
    private double height;
    private double weight;
    private String gender;
    private String weightUnit;
    private String heightUnit;
    private double bmi;
    private double idealWeight;
    private double fat;
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0));
        jLabel22 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        tab1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tab2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tab3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        tab4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tab5 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jp5 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        userid = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jp4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jp3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        timetb = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        caloriestb = new javax.swing.JTextField();
        choice1 = new java.awt.Choice();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        scaletb = new javax.swing.JTextField();
        opscore = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        intcombo = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        jp1 = new javax.swing.JPanel();
        dropDownHeightUnit = new javax.swing.JComboBox<>();
        dropDownWeightUnit = new javax.swing.JComboBox<>();
        lblBMI = new javax.swing.JLabel();
        lblBMIValue = new javax.swing.JLabel();
        lblIdealWeight = new javax.swing.JLabel();
        lblIdealWeightValue = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        lblFAT = new javax.swing.JLabel();
        lblFATValue = new javax.swing.JLabel();
        lblMessage = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        textAge = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        textHeightCM = new javax.swing.JTextField();
        textHeightFeet = new javax.swing.JTextField();
        textHeightInches = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        textWeight = new javax.swing.JTextField();
        textWeightLB = new javax.swing.JTextField();
        textWeightSt = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        dropDownGender = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        jp2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        bmivalue = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();

        jLabel22.setText("jLabel22");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tab1.setBackground(new java.awt.Color(255, 255, 255));
        tab1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab1MouseClicked(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\wgome\\Desktop\\college\\minproj\\calculate.jpg")); // NOI18N
        jLabel2.setText("Calculate");
        jLabel2.setOpaque(true);
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout tab1Layout = new javax.swing.GroupLayout(tab1);
        tab1.setLayout(tab1Layout);
        tab1Layout.setHorizontalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tab1Layout.setVerticalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(tab1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 280, -1));

        tab2.setBackground(new java.awt.Color(255, 255, 255));
        tab2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab2MouseClicked(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\wgome\\Desktop\\college\\minproj\\gym ex.png")); // NOI18N
        jLabel1.setText("Excercise");
        jLabel1.setOpaque(true);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout tab2Layout = new javax.swing.GroupLayout(tab2);
        tab2.setLayout(tab2Layout);
        tab2Layout.setHorizontalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tab2Layout.setVerticalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(tab2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 197, 280, -1));

        tab3.setBackground(new java.awt.Color(255, 255, 255));
        tab3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab3MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\wgome\\Desktop\\college\\minproj\\reward.png")); // NOI18N
        jLabel3.setText("Leaderboard");

        javax.swing.GroupLayout tab3Layout = new javax.swing.GroupLayout(tab3);
        tab3.setLayout(tab3Layout);
        tab3Layout.setHorizontalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel3)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        tab3Layout.setVerticalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(tab3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 291, -1, -1));

        jLabel5.setFont(new java.awt.Font("Gill Sans MT Condensed", 2, 60)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("myFit");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 25, 167, 53));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 89, 105, 10));

        tab4.setBackground(new java.awt.Color(255, 255, 255));
        tab4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab4MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setText("About us");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout tab4Layout = new javax.swing.GroupLayout(tab4);
        tab4.setLayout(tab4Layout);
        tab4Layout.setHorizontalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab4Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tab4Layout.setVerticalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(tab4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 485, 280, -1));

        tab5.setBackground(new java.awt.Color(255, 255, 255));
        tab5.setForeground(new java.awt.Color(255, 255, 255));
        tab5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab5MouseClicked(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel20.setText("myFit Card");

        javax.swing.GroupLayout tab5Layout = new javax.swing.GroupLayout(tab5);
        tab5.setLayout(tab5Layout);
        tab5Layout.setHorizontalGroup(
            tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );
        tab5Layout.setVerticalGroup(
            tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
        );

        jPanel1.add(tab5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 385, 280, -1));

        jLabel38.setText("jLabel38");
        jPanel1.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 650));

        jp5.setBackground(new java.awt.Color(204, 204, 0));

        jPanel5.setLayout(null);

        userid.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jPanel5.add(userid);
        userid.setBounds(110, 360, 640, 70);

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fitness_tracker/myfit.png"))); // NOI18N
        jPanel5.add(jLabel21);
        jLabel21.setBounds(0, 0, 840, 650);

        javax.swing.GroupLayout jp5Layout = new javax.swing.GroupLayout(jp5);
        jp5.setLayout(jp5Layout);
        jp5Layout.setHorizontalGroup(
            jp5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE)
        );
        jp5Layout.setVerticalGroup(
            jp5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
        );

        jp4.setBackground(new java.awt.Color(51, 51, 51));

        jLabel6.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 60)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Created by");

        jLabel7.setIcon(new javax.swing.ImageIcon("C:\\Users\\wgome\\Desktop\\college\\minproj\\pp.jpg")); // NOI18N
        jLabel7.setText("jLabel7");
        jLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 102, 51), null));

        jLabel8.setIcon(new javax.swing.ImageIcon("C:\\Users\\wgome\\Desktop\\college\\minproj\\cropped_image.png")); // NOI18N
        jLabel8.setText("jLabel7");
        jLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 153, 0), null));

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Alen john");

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Sydney gomes");

        jLabel11.setIcon(new javax.swing.ImageIcon("C:\\Users\\wgome\\Desktop\\college\\minproj\\WhatsApp Image 2020-10-27 at 1.25.26 AM.png")); // NOI18N
        jLabel11.setText("jLabel11");
        jLabel11.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 204, 51), null));

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText(" Junaid Khan");

        javax.swing.GroupLayout jp4Layout = new javax.swing.GroupLayout(jp4);
        jp4.setLayout(jp4Layout);
        jp4Layout.setHorizontalGroup(
            jp4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp4Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jp4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(144, 144, 144)
                .addGroup(jp4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp4Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 168, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56))
                    .addGroup(jp4Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64))))
            .addGroup(jp4Layout.createSequentialGroup()
                .addGap(253, 253, 253)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jp4Layout.setVerticalGroup(
            jp4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp4Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel6)
                .addGap(71, 71, 71)
                .addGroup(jp4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jp4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(211, Short.MAX_VALUE))
        );

        jp3.setBackground(new java.awt.Color(255, 255, 102));
        jp3.setLayout(null);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 0, 51));
        jLabel14.setText("  Rate your workout session");
        jp3.add(jLabel14);
        jLabel14.setBounds(160, 60, 465, 45);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(153, 255, 0));
        jLabel15.setText("Time spent in minutes");
        jp3.add(jLabel15);
        jLabel15.setBounds(70, 289, 188, 25);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(153, 255, 0));
        jLabel16.setText("Workout Intensity");
        jp3.add(jLabel16);
        jLabel16.setBounds(70, 185, 153, 25);
        jp3.add(timetb);
        timetb.setBounds(70, 332, 134, 30);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 255, 0));
        jLabel17.setText("Calories burnt");
        jp3.add(jLabel17);
        jLabel17.setBounds(70, 370, 134, 25);
        jp3.add(caloriestb);
        caloriestb.setBounds(70, 413, 134, 30);

        choice1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        choice1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                choice1ItemStateChanged(evt);
            }
        });
        jp3.add(choice1);
        choice1.setBounds(690, 20, 123, 29);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel18.setText("Select which mode you want");
        jp3.add(jLabel18);
        jLabel18.setBounds(392, 20, 288, 29);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 255, 0));
        jLabel19.setText("On a scale of 1-10 how satisfied are you with the workout?");
        jp3.add(jLabel19);
        jLabel19.setBounds(70, 469, 524, 25);

        scaletb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scaletbActionPerformed(evt);
            }
        });
        jp3.add(scaletb);
        scaletb.setBounds(70, 512, 141, 30);

        opscore.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        opscore.setForeground(new java.awt.Color(102, 255, 0));
        opscore.setText("              Your myFit Score is");
        opscore.setToolTipText("");
        jp3.add(opscore);
        opscore.setBounds(127, 538, 553, 38);

        jButton2.setBackground(new java.awt.Color(0, 255, 102));
        jButton2.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 51, 51));
        jButton2.setText("Calculate");
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jp3.add(jButton2);
        jButton2.setBounds(320, 590, 160, 40);

        intcombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Low", "High ", "Extreme" }));
        intcombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                intcomboItemStateChanged(evt);
            }
        });
        jp3.add(intcombo);
        intcombo.setBounds(70, 228, 211, 32);

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fitness_tracker/new.png"))); // NOI18N
        jp3.add(jLabel37);
        jLabel37.setBounds(0, 0, 840, 660);

        jp1.setBackground(new java.awt.Color(204, 204, 204));
        jp1.setForeground(new java.awt.Color(204, 0, 0));
        jp1.setLayout(null);

        dropDownHeightUnit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CM", "FT+IN" }));
        dropDownHeightUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropDownHeightUnitActionPerformed(evt);
            }
        });
        jp1.add(dropDownHeightUnit);
        dropDownHeightUnit.setBounds(549, 191, 85, 20);

        dropDownWeightUnit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "KG", "LB", "LB+ST" }));
        dropDownWeightUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropDownWeightUnitActionPerformed(evt);
            }
        });
        jp1.add(dropDownWeightUnit);
        dropDownWeightUnit.setBounds(549, 235, 85, 20);

        lblBMI.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        lblBMI.setText("BMI");
        jp1.add(lblBMI);
        lblBMI.setBounds(230, 280, 38, 17);

        lblBMIValue.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblBMIValue.setForeground(new java.awt.Color(0, 204, 102));
        lblBMIValue.setText("0");
        jp1.add(lblBMIValue);
        lblBMIValue.setBounds(240, 300, 10, 22);

        lblIdealWeight.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        lblIdealWeight.setText("IDEAL WEIGHT");
        jp1.add(lblIdealWeight);
        lblIdealWeight.setBounds(330, 280, 110, 17);

        lblIdealWeightValue.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblIdealWeightValue.setForeground(new java.awt.Color(0, 255, 153));
        lblIdealWeightValue.setText("0");
        jp1.add(lblIdealWeightValue);
        lblIdealWeightValue.setBounds(370, 300, 10, 22);

        jPanel6.setBackground(new java.awt.Color(153, 153, 153));

        jLabel23.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("BMI CALCULATOR");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(295, 295, 295))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
        );

        jp1.add(jPanel6);
        jPanel6.setBounds(0, 0, 835, 73);

        lblFAT.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        lblFAT.setText("FAT");
        jp1.add(lblFAT);
        lblFAT.setBounds(490, 280, 40, 17);

        lblFATValue.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblFATValue.setForeground(new java.awt.Color(0, 255, 153));
        lblFATValue.setText("0");
        jp1.add(lblFATValue);
        lblFATValue.setBounds(500, 300, 10, 22);

        lblMessage.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMessage.setBorder(new javax.swing.border.MatteBorder(null));
        jp1.add(lblMessage);
        lblMessage.setBounds(135, 344, 622, 40);

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel24.setText("Age");
        jp1.add(jLabel24);
        jLabel24.setBounds(140, 130, 30, 22);

        jLabel25.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(10, 143, 8));
        jLabel25.setText("BMI CLASSIFICATION CHART");
        jp1.add(jLabel25);
        jLabel25.setBounds(282, 402, 277, 15);

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel26.setText("Height");
        jp1.add(jLabel26);
        jLabel26.setBounds(135, 187, 51, 22);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(233, 89, 86));
        jLabel27.setText("VERY SEVERLY UNDERWEIGHT                                < 16");
        jp1.add(jLabel27);
        jLabel27.setBounds(165, 428, 482, 22);

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel28.setText("Weight");
        jp1.add(jLabel28);
        jLabel28.setBounds(135, 222, 55, 41);

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(239, 108, 1));
        jLabel29.setText("SEVERLY UNDERWEIGHT                                          16 - 17");
        jp1.add(jLabel29);
        jLabel29.setBounds(165, 461, 522, 22);

        textAge.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textAgeFocusLost(evt);
            }
        });
        textAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textAgeActionPerformed(evt);
            }
        });
        textAge.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textAgeKeyTyped(evt);
            }
        });
        jp1.add(textAge);
        textAge.setBounds(224, 133, 140, 20);

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 102, 0));
        jLabel30.setText("UNDERWEIGHT                                                         17 - 18.5");
        jp1.add(jLabel30);
        jLabel30.setBounds(165, 494, 506, 22);

        jPanel2.setForeground(new java.awt.Color(255, 204, 204));
        jPanel2.setOpaque(false);
        jPanel2.setLayout(null);

        textHeightCM.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textHeightCMFocusLost(evt);
            }
        });
        textHeightCM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textHeightCMActionPerformed(evt);
            }
        });
        textHeightCM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textHeightCMKeyTyped(evt);
            }
        });
        jPanel2.add(textHeightCM);
        textHeightCM.setBounds(0, 30, 140, 30);

        textHeightFeet.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textHeightFeetFocusLost(evt);
            }
        });
        textHeightFeet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textHeightFeetActionPerformed(evt);
            }
        });
        textHeightFeet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textHeightFeetKeyTyped(evt);
            }
        });
        jPanel2.add(textHeightFeet);
        textHeightFeet.setBounds(0, 30, 60, 30);

        textHeightInches.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textHeightInchesFocusLost(evt);
            }
        });
        textHeightInches.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textHeightInchesKeyTyped(evt);
            }
        });
        jPanel2.add(textHeightInches);
        textHeightInches.setBounds(70, 30, 70, 30);

        jp1.add(jPanel2);
        jPanel2.setBounds(220, 147, 150, 60);

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(92, 152, 18));
        jLabel31.setText("NORMAL                                                                     18.5 - 25");
        jp1.add(jLabel31);
        jLabel31.setBounds(165, 522, 522, 22);

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 0, 0));
        jLabel32.setText("OVERWEIGHT                                                            25  -  30");
        jp1.add(jLabel32);
        jLabel32.setBounds(165, 555, 507, 22);

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(153, 0, 0));
        jLabel33.setText("OBESE CLASS I                                                           30 - 35");
        jp1.add(jLabel33);
        jLabel33.setBounds(165, 588, 491, 22);

        jPanel3.setForeground(new java.awt.Color(255, 102, 102));
        jPanel3.setOpaque(false);
        jPanel3.setLayout(null);

        textWeight.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textWeightFocusLost(evt);
            }
        });
        textWeight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textWeightActionPerformed(evt);
            }
        });
        textWeight.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textWeightKeyTyped(evt);
            }
        });
        jPanel3.add(textWeight);
        textWeight.setBounds(0, 0, 140, 30);

        textWeightLB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textWeightLBFocusLost(evt);
            }
        });
        textWeightLB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textWeightLBActionPerformed(evt);
            }
        });
        textWeightLB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textWeightLBKeyTyped(evt);
            }
        });
        jPanel3.add(textWeightLB);
        textWeightLB.setBounds(-10, 0, 70, 30);

        textWeightSt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textWeightStFocusLost(evt);
            }
        });
        textWeightSt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textWeightStKeyTyped(evt);
            }
        });
        jPanel3.add(textWeightSt);
        textWeightSt.setBounds(60, 0, 80, 30);

        jp1.add(jPanel3);
        jPanel3.setBounds(220, 225, 150, 39);

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(51, 0, 0));
        jLabel34.setText("OBESE CLASS II                                                          35 - 40");
        jp1.add(jLabel34);
        jLabel34.setBounds(165, 621, 495, 22);

        dropDownGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MALE", "FEMALE" }));
        dropDownGender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropDownGenderActionPerformed(evt);
            }
        });
        jp1.add(dropDownGender);
        dropDownGender.setBounds(549, 133, 85, 20);

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fitness_tracker/Webp.net-resizeimage (1).png"))); // NOI18N
        jLabel35.setText("jLabel35");
        jp1.add(jLabel35);
        jLabel35.setBounds(0, 70, 840, 580);

        jp2.setBackground(new java.awt.Color(51, 51, 51));
        jp2.setLayout(null);

        jLabel13.setFont(new java.awt.Font("Tahoma", 3, 48)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Please enter your BMI");
        jp2.add(jLabel13);
        jLabel13.setBounds(150, 220, 580, 58);

        bmivalue.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        bmivalue.setForeground(new java.awt.Color(204, 204, 204));
        bmivalue.setText(" BMI");
        bmivalue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bmivalueMouseClicked(evt);
            }
        });
        bmivalue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bmivalueActionPerformed(evt);
            }
        });
        jp2.add(bmivalue);
        bmivalue.setBounds(370, 330, 89, 42);

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(102, 102, 102));
        jButton1.setText("Get Fit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jp2.add(jButton1);
        jButton1.setBounds(360, 400, 114, 53);

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fitness_tracker/pexels-philip-ackermann-878151.jpg.png"))); // NOI18N
        jp2.add(jLabel36);
        jLabel36.setBounds(0, 0, 840, 590);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jp1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jp3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jp2, javax.swing.GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jp4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jp5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jp1, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jp3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jp2, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jp4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jp5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void tab1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab1MouseClicked
    jp1.setVisible(true);
      jp2.setVisible(false);
        jp3.setVisible(false);
          jp4.setVisible(false);
          jp5.setVisible(false);
          
          
    
    }//GEN-LAST:event_tab1MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void tab2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab2MouseClicked
    jp1.setVisible(false);
      jp2.setVisible(true);
        jp3.setVisible(false);
          jp4.setVisible(false);
              jp5.setVisible(false);
          
            // TODO add your handling code here:
    }//GEN-LAST:event_tab2MouseClicked

    private void tab3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab3MouseClicked
       jp1.setVisible(false);
       jp2.setVisible(false);
       jp3.setVisible(true);
       jp4.setVisible(false);
           jp5.setVisible(false);
          
    
    }//GEN-LAST:event_tab3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseClicked

    private void tab4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab4MouseClicked
        jp1.setVisible(false);
      jp2.setVisible(false);
        jp3.setVisible(false);
          jp4.setVisible(true);
              jp5.setVisible(false);
          
    
    }//GEN-LAST:event_tab4MouseClicked

    private void bmivalueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bmivalueActionPerformed
    
    }//GEN-LAST:event_bmivalueActionPerformed

    private void bmivalueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bmivalueMouseClicked
          bmivalue.setText("");
    }//GEN-LAST:event_bmivalueMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
    Float bmicond = Float.parseFloat(bmivalue.getText());
    if(bmicond<18.5) {
        
        Desktop browser = Desktop.getDesktop();
        try{
            browser.browse(new URI("file:///C:/Users/wgome/Desktop/college/test.html"));
        }
        catch(IOException | URISyntaxException err){
        
            
    }
      
    }
    else if(18.5<=bmicond && 25>=bmicond){
        
           Desktop browser = Desktop.getDesktop();
        try{
            browser.browse(new URI("file:///C:/Users/wgome/Desktop/college/normal.html"));
        }
        catch(IOException | URISyntaxException err){
        
        }
    }
          else{
           Desktop browser = Desktop.getDesktop();
        try{
            browser.browse(new URI("file:///C:/Users/wgome/Desktop/college/OBESE.HTML"));
        }
        catch(IOException | URISyntaxException err){
                  }
                  }
    
   
    }//GEN-LAST:event_jButton1ActionPerformed

    private void scaletbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scaletbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_scaletbActionPerformed
       PreparedStatement ps;
                 ResultSet rs;       
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        My_CNX connect = new My_CNX(); 
 
                 
        double score1 = calculatescore();
        Leaderboard form = new Leaderboard();
            form.setVisible(true);
            form.pack();
          login_screen l = new login_screen();
        // String b= f.getuser();
        // System.out.println(b);
         String b =username;
      
          String registerUserQuery = "INSERT INTO `leaderboard`(`username`, `score`) VALUES (?,?) " ;
         try{  
           ps = connect.getConnection().prepareStatement(registerUserQuery);
           ps.setString(1,b);
           ps.setDouble(2,score1);
           ps.execute();
        
        
         }
         catch(SQLException ex){
        Logger.getLogger(Register_form.class.getName()).log(Level.SEVERE, null, ex);
         }
             
         
    }//GEN-LAST:event_jButton2ActionPerformed

    private void intcomboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_intcomboItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_intcomboItemStateChanged

    private void tab5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab5MouseClicked
          jp1.setVisible(false);
      jp2.setVisible(false);
        jp3.setVisible(false);
          jp4.setVisible(false);
              jp5.setVisible(true);
    }//GEN-LAST:event_tab5MouseClicked

    private void dropDownGenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropDownGenderActionPerformed
        if (dropDownGender.getSelectedIndex() == 0) {
            this.gender = "MALE";
            calculateFAT();

        } else {
            this.gender = "FEMALE";
            calculateFAT();
        }
        calculateIdealWeight();
    }//GEN-LAST:event_dropDownGenderActionPerformed

    private void textWeightStKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textWeightStKeyTyped
        String temp = textWeightSt.getText();
        if (!(Character.isDigit(evt.getKeyChar()) || evt.getKeyChar() == '.')) {
            getToolkit().beep();
            evt.consume();
        }
        if (temp.length() > 2) {
            temp = temp.substring(0, 2);
            textWeightSt.setText(temp);
        }
    }//GEN-LAST:event_textWeightStKeyTyped

    private void textWeightStFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textWeightStFocusLost
        if(!"".equals(textWeightLB.getText())){
            double temp = Double.parseDouble(textWeightLB.getText()) * 0.453592 +
            Double.parseDouble(textWeightSt.getText()) * 6.35029;
            this.weight = temp;
            if(this.height != 0){
                calculateBMI();
                calculateIdealWeight();
                if(this.age != 0){
                    calculateFAT();
                }
            }
        }
    }//GEN-LAST:event_textWeightStFocusLost

    private void textWeightLBKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textWeightLBKeyTyped
        String temp = textWeightLB.getText();
        if (!(Character.isDigit(evt.getKeyChar()) || evt.getKeyChar() == '.')) {
            getToolkit().beep();
            evt.consume();
        }
        if (temp.length() > 4) {
            temp = temp.substring(0, 4);
            textWeightLB.setText(temp);
        }
    }//GEN-LAST:event_textWeightLBKeyTyped

    private void textWeightLBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textWeightLBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textWeightLBActionPerformed

    private void textWeightLBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textWeightLBFocusLost
        if(!"".equals(textWeightLB.getText())){
            double temp = Double.parseDouble(textWeightLB.getText()) * 0.453592 +
            Double.parseDouble(textWeightSt.getText()) * 6.35029;
            this.weight = temp;
            if(this.height != 0){
                calculateBMI();
                calculateIdealWeight();
                if(this.age != 0){
                    calculateFAT();
                }
            }
        }
    }//GEN-LAST:event_textWeightLBFocusLost

    private void textWeightKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textWeightKeyTyped
        String temp = textWeight.getText();
        if (!(Character.isDigit(evt.getKeyChar()) || evt.getKeyChar() == '.')) {
            getToolkit().beep();
            evt.consume();
        }
        if (temp.length() > 4) {
            temp = temp.substring(0, 4);
            textWeight.setText(temp);
        }
    }//GEN-LAST:event_textWeightKeyTyped

    private void textWeightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textWeightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textWeightActionPerformed

    private void textWeightFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textWeightFocusLost
        if (dropDownWeightUnit.getSelectedIndex() == 0) {
            if (!"".equals(textWeight.getText())) {
                this.weight = Double.parseDouble(textWeight.getText());
                if(this.height != 0){
                    calculateBMI();
                    calculateIdealWeight();
                    if(this.age != 0){
                        calculateFAT();
                    }
                }
            }
        }
        if (dropDownWeightUnit.getSelectedIndex() == 1) {
            if (!"".equals(textWeight.getText())) {
                this.weight = Double.parseDouble(textWeight.getText()) * 0.453592;
                if (this.height != 0) {
                    calculateBMI();
                }
            }
        }
    }//GEN-LAST:event_textWeightFocusLost

    private void textHeightInchesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textHeightInchesKeyTyped
        String temp = textHeightInches.getText();
        if (!(Character.isDigit(evt.getKeyChar()) || evt.getKeyChar() =='.')) {
            getToolkit().beep();
            evt.consume();
        }
        if(temp.length() > 3){
            temp = temp.substring(0, 3);
            textHeightInches.setText(temp);
        }
    }//GEN-LAST:event_textHeightInchesKeyTyped

    private void textHeightInchesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textHeightInchesFocusLost
        if (!"".equals(textHeightFeet.getText())) {
            double temp = Double.parseDouble(textHeightFeet.getText()) * 12
            + Double.parseDouble(textHeightInches.getText());
            temp = temp * 2.54;
            this.height = temp;
            if (this.weight != 0) {
                calculateBMI();
                calculateIdealWeight();
            }
        }
    }//GEN-LAST:event_textHeightInchesFocusLost

    private void textHeightFeetKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textHeightFeetKeyTyped
        String temp = textHeightFeet.getText();
        if (!(Character.isDigit(evt.getKeyChar()) || evt.getKeyChar() == '.')) {
            getToolkit().beep();
            evt.consume();
        }

        if(temp.length() > 2){
            temp = temp.substring(0, 2);
            textHeightFeet.setText(temp);
        }
    }//GEN-LAST:event_textHeightFeetKeyTyped

    private void textHeightFeetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textHeightFeetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textHeightFeetActionPerformed

    private void textHeightFeetFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textHeightFeetFocusLost
        if(!"".equals(textHeightFeet.getText())){
            double temp = Double.parseDouble(textHeightFeet.getText()) * 12
            + Double.parseDouble(textHeightInches.getText());
            temp = temp * 2.54;
            this.height = temp;
            if(this.weight != 0){
                calculateBMI();
                calculateIdealWeight();
            }
        }
    }//GEN-LAST:event_textHeightFeetFocusLost

    private void textHeightCMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textHeightCMKeyTyped
        String temp = textHeightCM.getText();
        if (!(Character.isDigit(evt.getKeyChar()) || evt.getKeyChar() == '.')) {
            getToolkit().beep();
            evt.consume();
        }
        if(temp.length() > 4){
            temp = temp.substring(0, 4);
            textHeightCM.setText(temp);
        }
    }//GEN-LAST:event_textHeightCMKeyTyped

    private void textHeightCMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textHeightCMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textHeightCMActionPerformed

    private void textHeightCMFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textHeightCMFocusLost
        if(!"".equals(textHeightCM.getText()) ){
            this.setHeight(Double.parseDouble(textHeightCM.getText()));
            if(this.weight != 0){
                calculateBMI();
                calculateIdealWeight();
            }
        }
    }//GEN-LAST:event_textHeightCMFocusLost

    private void textAgeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textAgeKeyTyped
        String temp = textAge.getText();
        if (!Character.isDigit(evt.getKeyChar())) {
            getToolkit().beep();
            evt.consume();
        }
        if(temp.length() > 2){
            temp = temp.substring(0, 2);
            textAge.setText(temp);
        }
    }//GEN-LAST:event_textAgeKeyTyped

    private void textAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textAgeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textAgeActionPerformed

    private void textAgeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textAgeFocusLost
        if(!"".equals(textAge.getText())){
            this.setAge(Integer.parseInt(textAge.getText()));
            calculateFAT();
        }
    }//GEN-LAST:event_textAgeFocusLost

    private void dropDownWeightUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropDownWeightUnitActionPerformed
        if (dropDownWeightUnit.getSelectedIndex() == 0) {
            textWeightLB.hide();
            textWeightSt.hide();
            textWeight.show();
            this.weightUnit = "KG";
            if (this.idealWeight != 0 ) {
                String temp = "" + this.getIdealWeight();
                lblIdealWeightValue.setText(temp.substring(0,6) + "KG");
            }
        } else if (dropDownWeightUnit.getSelectedIndex() == 1) {
            this.weightUnit = "LB";
            textWeightLB.hide();
            textWeightSt.hide();
            textWeight.show();
            if (this.idealWeight != 0) {
                String temp = "" + this.getIdealWeight() * 2.20462;
                lblIdealWeightValue.setText(temp.substring(0,6) + "LBS");
            }
        } else if (dropDownWeightUnit.getSelectedIndex() == 2) {
            this.weightUnit = "LB+ST";
            textWeightLB.show();
            textWeightSt.show();
            textWeight.hide();
            if (this.idealWeight != 0) {
                String temp = "" + this.getIdealWeight() * 2.20462;
                lblIdealWeightValue.setText(temp.substring(0,6) + "LBS");
            }
        }
    }//GEN-LAST:event_dropDownWeightUnitActionPerformed

    private void dropDownHeightUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropDownHeightUnitActionPerformed
        if (dropDownHeightUnit.getSelectedIndex() == 0) {
            textHeightCM.show();
            textHeightFeet.hide();
            textHeightInches.hide();
            this.heightUnit = "CM";
        }
        if (dropDownHeightUnit.getSelectedIndex() == 1) {
            textHeightCM.hide();
            textHeightFeet.show();
            textHeightInches.show();
            this.heightUnit = "FT+IN";
        }
    }//GEN-LAST:event_dropDownHeightUnitActionPerformed

    private void choice1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_choice1ItemStateChanged
        JOptionPane.showMessageDialog(this,"Switched to "+choice1.getSelectedItem()+" mode");
    }//GEN-LAST:event_choice1ItemStateChanged
   
     
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_Form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bmivalue;
    private javax.swing.JTextField caloriestb;
    private java.awt.Choice choice1;
    private javax.swing.JComboBox<String> dropDownGender;
    private javax.swing.JComboBox<String> dropDownHeightUnit;
    private javax.swing.JComboBox<String> dropDownWeightUnit;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JComboBox<String> intcombo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel jp1;
    private javax.swing.JPanel jp2;
    private javax.swing.JPanel jp3;
    private javax.swing.JPanel jp4;
    private javax.swing.JPanel jp5;
    private javax.swing.JLabel lblBMI;
    private javax.swing.JLabel lblBMIValue;
    private javax.swing.JLabel lblFAT;
    private javax.swing.JLabel lblFATValue;
    private javax.swing.JLabel lblIdealWeight;
    private javax.swing.JLabel lblIdealWeightValue;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel opscore;
    private javax.swing.JTextField scaletb;
    private javax.swing.JPanel tab1;
    private javax.swing.JPanel tab2;
    private javax.swing.JPanel tab3;
    private javax.swing.JPanel tab4;
    private javax.swing.JPanel tab5;
    private javax.swing.JTextField textAge;
    private javax.swing.JTextField textHeightCM;
    private javax.swing.JTextField textHeightFeet;
    private javax.swing.JTextField textHeightInches;
    private javax.swing.JTextField textWeight;
    private javax.swing.JTextField textWeightLB;
    private javax.swing.JTextField textWeightSt;
    private javax.swing.JTextField timetb;
    private javax.swing.JLabel userid;
    // End of variables declaration//GEN-END:variables
}
