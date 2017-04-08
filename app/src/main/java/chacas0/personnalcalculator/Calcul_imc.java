package chacas0.personnalcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;


public class Calcul_imc extends AppCompatActivity {
    Globals g = Globals.getInstance(); //Global variables

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul_imc);

        /**
         * @TODO [1]+Implement the test of the CalculImc Here
         */

        /**
         * @NOTE IMC = poids / taille²
         */

        if (g.currentOneExists()){
            //*-> Form already filled
            final Someone one = g.getCurrentOne();

            //We add the height to the form
            Float aTaille = one.getHeight();
            String oneTaille = Float.toString(aTaille);
            ((TextView) findViewById(R.id.imc_et_taille)).setText(oneTaille);

            //We check the right unit
            ((RadioButton) findViewById(R.id.imc_unit_btn_m)).setChecked(true);
            ((RadioButton) findViewById(R.id.imc_unit_btn_cm)).setChecked(false);

            //We add the weight to the form
            Float aPoids = one.getWeight();
            String onePoids = Float.toString(aPoids);
            ((TextView) findViewById(R.id.imc_et_poids)).setText(onePoids);

            //When the user valids the form to calculs
            final Button btnCalc = (Button) findViewById(R.id.imc_menu_valid);
            //on ajoute un listener sur le click du bouton b_valider
            btnCalc.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CalculImc calc_imc = new CalculImc(one);
                    String imc[] = calc_imc.calcul_imc(); //*-> Here we get the imc + the comment

                    ((TextView) findViewById(R.id.imc_resultat_value)).setText(imc[0]);
                    ((TextView) findViewById(R.id.imc_comm_tv_value)).setText(imc[1]);
                }
            });
        }else{
            //*-> Empty form
        }


//        //Fetch the creap_btn_raz button
//        final Button btnReset = (Button) findViewById(R.id.creap_btn_raz);
//        //what we need to do on click
//        btnReset.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                ((EditText) findViewById(R.id.lay_name_et_name)).setText("");
//                ((EditText) findViewById(R.id.creap_lay_age_et_value)).setText("");
//                ((EditText) findViewById(R.id.creap_lay_height_et_value)).setText("");
//                ((EditText) findViewById(R.id.creap_lay_weight_et_value)).setText("_");
//
//                ((RadioGroup) findViewById(R.id.creap_rad_sexe_rb)).clearCheck();
//                ((RadioGroup) findViewById(R.id.creap_rad_unit_rb)).clearCheck();
//            }
//        });
//
//        //=> Button to go home
//        final Button btn_home = (Button) findViewById(R.id.creap_lay_home_btn);
//        //_ What to do on click
//        btn_home.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                Intent goingTo = new Intent(Create_pers.this, Home.class);
//                startActivity(goingTo);
//            }
//        });
//
//        //Fetch the Create button
//        final Button btnCrea = (Button) findViewById(R.id.creap_btn_create);
//        //Add the listenner on the b_valider button
//        btnCrea.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                //Get the stats as String vars
//                String nom = ((EditText) findViewById(R.id.lay_name_et_name)).getText().toString();
//                String poids = ((EditText) findViewById(R.id.creap_lay_weight_et_value)).getText().toString();
//                String taille = ((EditText) findViewById(R.id.creap_lay_height_et_value)).getText().toString();
//                String age = ((EditText) findViewById(R.id.creap_lay_age_et_value)).getText().toString();
//                try{            //error management
//                    Float valuePoids = Float.valueOf(poids);
//                    Float valueTaille = Float.valueOf(taille);
//                    int valueAge = Integer.parseInt(age);
//
//                    //GESTION DE L'UNITE DE MESURE!!!!//
//                    if(((RadioGroup) findViewById(R.id.creap_rad_unit_rb)).getCheckedRadioButtonId() == R.id.creap_rad_unit_btn_cm){
//                        //Centimètres en mètres
//                        valueTaille=valueTaille/100;
//                    }else if(((RadioGroup) findViewById(R.id.creap_rad_unit_rb)).getCheckedRadioButtonId() != R.id.creap_rad_unit_btn_m){
//                        Toast.makeText(getApplicationContext(), "Aucune unité sélectionné!", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    if(valueTaille == 0 || valuePoids == 0){
//                        Toast.makeText(getApplicationContext(), "Poids ou taille est nulle /!\\", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    char valueSexe;
//                    //SEXE!!!!!//
//                    if(((RadioGroup) findViewById(R.id.creap_rad_sexe_rb)).getCheckedRadioButtonId() == R.id.sexe_btn_m){
//                        valueSexe = 'M';
//                    }else if(((RadioGroup) findViewById(R.id.creap_rad_sexe_rb)).getCheckedRadioButtonId() == R.id.sexe_btn_f){
//                        valueSexe = 'F';
//                    }else{
//                        Toast.makeText(getApplicationContext(), "Aucune unité sélectionné!", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    //_ Add the object in the list
//                    if(valueSexe == 'F'){
//                        Woman person = new Woman(nom, valueAge, valueTaille, valuePoids);
//                        g.addOne(person);
//                    }else if(valueSexe == 'M'){
//                        Man person = new Man(nom, valueAge, valueTaille, valuePoids);
//                        g.addOne(person);
//                    }else{
//                        //If something bad happends HERE
//                        Toast.makeText(Create_pers.this, "Erreur lors de la création de la personne; ", Toast.LENGTH_SHORT).show();
//                    }
//                }catch(Exception e){
//                    //Si une erreur se produit dans le code précédent
//                    Toast.makeText(Create_pers.this, "Erreur de saisie de poids ou de taille ", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//        });


        /**
         * @TODO The form already filled if a person has already been created
         */
    }





}
