package chacas0.personalcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class Calcul_imc extends AppCompatActivity {
    /**
     * @see Globals In order to create "Global Variables"
     */
    Globals g = Globals.getInstance(); //Global variables

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul_imc);

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
        }
            //*-> Empty form
//            Toast.makeText(getApplicationContext(), "passe ds le else", Toast.LENGTH_SHORT).show();
            //on récupère le bouton b_valider
            final Button btnCalc = (Button) findViewById(R.id.imc_menu_valid);
            //on ajoute un listener sur le click du bouton b_valider
            btnCalc.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String poids = ((EditText) findViewById(R.id.imc_et_poids)).getText().toString();
                    String taille = ((EditText) findViewById(R.id.imc_et_taille)).getText().toString();
                    Float valuePoids = Float.valueOf(poids);
                    Float valueTaille = Float.valueOf(taille);
                    try {            //error management
                        // MANAGING THE UNIT !!!!//
                        if (((RadioGroup) findViewById(R.id.imc_rad_unit)).getCheckedRadioButtonId() == R.id.imc_unit_btn_cm) {
                            //Centimetres to metres
                            valueTaille = valueTaille / 100;
                        } else if (((RadioGroup) findViewById(R.id.imc_rad_unit)).getCheckedRadioButtonId() != R.id.imc_unit_btn_m) {
                            Toast.makeText(getApplicationContext(), "Aucune unité sélectionné!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (valueTaille == 0 || valuePoids == 0) {
                            Toast.makeText(getApplicationContext(), "Poids ou taille est nulle /!\\", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        //LE CALCUL
                        Float result = valuePoids / (valueTaille * valueTaille);
                        ((TextView) findViewById(R.id.imc_resultat_value)).setText(result.toString());
                        //THE COMMENT
                        String com = "";
                        if (result < 16) {
                            com = "Manger beaucoup plus!";
                        } else if (result < 18.5) {
                            com = "Mangez plus!";
                        } else if (result < 25) {
                            com = "IMC normal.";
                        } else if (result < 30) {
                            com = "Léger surpoids!";
                        } else if (result < 35) {
                            com = "Régime + Sport recommandés";
                        } else if (result < 40) {
                            com = "Surpoids inquiétant!";
                        } else {
                            com = "Consultez un médecin!";

                            //((TextView) findViewById(R.id.tv_com)).setText(com);
                        }
                        ((TextView) findViewById(R.id.imc_comm_tv_value)).setText(com);
                    } catch (Exception e) {
                        //Si une erreur se produit dans le code précédent
                        Toast.makeText(Calcul_imc.this, "Erreur de saisie de poids ou de taille: " + e, Toast.LENGTH_SHORT).show();

                    }
                }
            });

        /**
         * Allowing the reset of the form
         */
        //we get the reset button
        final Button btnReset = (Button) findViewById(R.id.imc_menu_raz);
        //what we do when we click on it
        btnReset.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //Texts
                ((EditText) findViewById(R.id.imc_et_poids)).setText("");
                ((EditText) findViewById(R.id.imc_et_taille)).setText("");
                ((TextView) findViewById(R.id.imc_resultat_value)).setText("Calculez moi :-)");
                ((TextView) findViewById(R.id.imc_comm_tv_value)).setText("_");
                //RadioGroups
                ((RadioGroup) findViewById(R.id.imc_rad_unit)).clearCheck();
            }
        });

        /**
         * If changes happens to the RadioGroup of units:
         */
        RadioGroup radGrp = (RadioGroup) findViewById(R.id.imc_rad_unit);
        radGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged(RadioGroup group, int checkedId){
                ((TextView) findViewById(R.id.imc_resultat_value)).setText(" _");
                ((TextView) findViewById(R.id.imc_comm_tv_value)).setText(" _");
            }
        });


        //=> Button to go home
        final Button imc_home = (Button) findViewById(R.id.imc_home);
        //_ What to do on click
        imc_home.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent goingTo = new Intent(Calcul_imc.this, Home.class);
                g.setCalculType("imc");
                startActivity(goingTo);
            }
        });


        /**
         * If changes happens to the weight:
         */
        final EditText etPoids = (EditText) findViewById(R.id.imc_et_poids);
        etPoids.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s){
                //
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
                //
            }
            public void onTextChanged(CharSequence s, int start, int before, int count){
//                Toast.makeText(getApplicationContext(), "Champ changé", Toast.LENGTH_SHORT).show();
                ((TextView) findViewById(R.id.imc_resultat_value)).setText(" _");
                ((TextView) findViewById(R.id.imc_comm_tv_value)).setText(" _");
            }
        });

        /**
         * If changes happens to the height:
         */
        final EditText etTaille = (EditText) findViewById(R.id.imc_et_taille);
        etTaille.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s){
                //
            }
            public void beforeTextChanged(CharSequence s, int start, int before, int after){
                //
            }
            public void onTextChanged(CharSequence s, int start, int before, int count){
                ((TextView) findViewById(R.id.imc_resultat_value)).setText(" _");
                ((TextView) findViewById(R.id.imc_comm_tv_value)).setText(" _");
            }
        });

    }





}
