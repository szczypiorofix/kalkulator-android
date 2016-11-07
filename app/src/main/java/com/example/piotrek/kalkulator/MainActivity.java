package com.example.piotrek.kalkulator;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView wynik;

    private enum Dzialanie {
        nic, Dodawanie, Odejmowanie, Mnozenie, Dzielenie
    }

    private Dzialanie dzialanie;
    private BigDecimal wP = new BigDecimal("0"), wJP = new BigDecimal("0"), wA = new BigDecimal("0");

    private static final BigDecimal TWO = BigDecimal.valueOf(2L);
    private String wynikS = "0";
    private boolean przecinek = false, poprzecinku = false, rownanie = false, pierw = false, odNowa = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnDodaj, btnOdejmij, btnPomnoz, btnPodziel, btnRownaSie, btnPierwiastek, btnPrzecinek, btnPlusMinus;
        Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnC, btnBack;

        btn0 = (Button) findViewById(R.id.b0);
        btn1 = (Button) findViewById(R.id.b1);
        btn2 = (Button) findViewById(R.id.b2);
        btn3 = (Button) findViewById(R.id.b3);
        btn4 = (Button) findViewById(R.id.b4);
        btn5 = (Button) findViewById(R.id.b5);
        btn6 = (Button) findViewById(R.id.b6);
        btn7 = (Button) findViewById(R.id.b7);
        btn8 = (Button) findViewById(R.id.b8);
        btn9 = (Button) findViewById(R.id.b9);

        btnC = (Button) findViewById(R.id.bC);
        btnDodaj = (Button) findViewById(R.id.bDodaj);
        btnOdejmij = (Button) findViewById(R.id.bOdejmij);
        btnPomnoz = (Button) findViewById(R.id.bPomnoz);
        btnPodziel = (Button) findViewById(R.id.bPodziel);
        btnRownaSie = (Button) findViewById(R.id.bRownaSie);
        btnPierwiastek = (Button) findViewById(R.id.bSqrt);
        btnPrzecinek = (Button) findViewById(R.id.bPrzecinek);
        btnBack = (Button) findViewById(R.id.bBack);
        btnPlusMinus = (Button) findViewById(R.id.bPlusMinus);

        wynik = (TextView) findViewById(R.id.eWynik);

        assert btn0 != null;
        assert btn1 != null;
        assert btn2 != null;
        assert btn3 != null;
        assert btn4 != null;
        assert btn5 != null;
        assert btn6 != null;
        assert btn7 != null;
        assert btn8 != null;
        assert btn9 != null;
        assert btnC != null;
        assert btnDodaj != null;
        assert btnOdejmij != null;
        assert btnPodziel != null;
        assert btnPomnoz != null;
        assert btnRownaSie != null;
        assert btnPierwiastek != null;
        assert btnBack != null;
        assert btnPlusMinus != null;
        assert btnPrzecinek != null;

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnDodaj.setOnClickListener(this);
        btnOdejmij.setOnClickListener(this);
        btnPomnoz.setOnClickListener(this);
        btnPodziel.setOnClickListener(this);
        btnRownaSie.setOnClickListener(this);
        btnPierwiastek.setOnClickListener(this);
        btnPrzecinek.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnPlusMinus.setOnClickListener(this);

        wynik.setText("0");
        dzialanie = Dzialanie.nic;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.b0: {
                policz("0");
                break;
            }
            case R.id.b1: {
                policz("1");
                break;
            }
            case R.id.b2: {
                policz("2");
                break;
            }
            case R.id.b3: {
                policz("3");
                break;
            }
            case R.id.b4: {
                policz("4");
                break;
            }
            case R.id.b5: {
                policz("5");
                break;
            }
            case R.id.b6: {
                policz("6");
                break;
            }
            case R.id.b7: {
                policz("7");
                break;
            }
            case R.id.b8: {
                policz("8");
                break;
            }
            case R.id.b9: {
                policz("9");
                break;
            }
            case R.id.bSqrt: {
                policz("\u221A");
                break;
            }
            case R.id.bPrzecinek: {
                policz(".");
                break;
            }
            case R.id.bBack: {
                policz("<=");
                break;
            }
            case R.id.bPlusMinus: {
                policz("+/-");
                break;
            }
            case R.id.bC: {
                policz("C");
                break;
            }
            case R.id.bDodaj: {
                policz("+");
                break;
            }
            case R.id.bOdejmij: {
                policz("-");
                break;
            }
            case R.id.bPomnoz: {
                policz("\u00D7");
                break;
            }
            case R.id.bPodziel: {
                policz("\u00F7");
                break;
            }
            case R.id.bRownaSie: {
                policz("=");
                break;
            }
        }

        wynik.setText(wynikS);
    }

    private static BigDecimal sqrt(BigDecimal x, MathContext mc) {
        BigDecimal g = x.divide(TWO, mc);
        boolean done = false;
        final int maxIterations = mc.getPrecision() + 1;
        for (int i = 0; !done && i < maxIterations; i++) {
            // r = (x/g + g) / 2
            BigDecimal r = x.divide(g, mc);
            r = r.add(g);
            r = r.divide(TWO, mc);
            done = r.equals(g);
            g = r;
        }
        return g;
    }

    private void policz(String x)
    {
        boolean blad = false;
        if (x.equals("C"))
        {
            wynikS = "0";
            wA = new BigDecimal("0");
            wP = new BigDecimal("0");
            wJP = new BigDecimal("0");
            dzialanie = Dzialanie.nic;
            przecinek = false;
            poprzecinku = false;
            odNowa = true;
        }
        if (x.equals("<="))
        {
            if ((wA.compareTo(BigDecimal.valueOf(0)) != 0) && (wynikS.length() > 0))
            {
                int cut = wynikS.length();
                if ((wynikS.charAt(0) == '-') && (wynikS.length()== 2)) wynikS = "";
                if (wynikS.length() > 1) wynikS = wynikS.substring(0, cut-1);
                else wynikS = "0";
                wA = new BigDecimal(wynikS);
            }
        }
        if (x.equals("+/-"))
        {
            wA = wA.multiply(new BigDecimal("-1"));
            wP = wA;
            wynikS = String.valueOf(wA);
            przecinek = false;
        }

        if (x.equals(".") && (!poprzecinku)) {
            przecinek = true;
        }

        if (x.equals("0") || x.equals("1") || x.equals("2") || x.equals("3") || x.equals("4") || x.equals("5") || x.equals("6") || x.equals("7") || x.equals("8") || x.equals("9"))
        {
            if (wynikS.startsWith("0") && !poprzecinku) {
                wynikS="";
            }

            if ((przecinek) && (wynikS.length() == 0))
            {
                wynikS = "0";
            }
            if ((przecinek) && (wynikS.length() > 0))
            {
                wynikS = wynikS + ".";
                poprzecinku = true;
                przecinek = false;
            }

            if ((odNowa) || (pierw))
            {
                wynikS = "";
                odNowa = false;
                pierw = false;
            }

            if (!odNowa)
            {
                wynikS = wynikS + x;
                odNowa = false;
            }
            else {
                if (!poprzecinku) {
                    odNowa = true;
                    wynikS = x;
                }
                else {
                    odNowa = false;
                    wynikS=wynikS+x;
                }
            }
            wA = new BigDecimal(wynikS);
            wP = wA;
        }

        if ((x.equals("+")) || (x.equals("-")) || (x.equals("\u00D7")) || (x.equals("\u00F7")))
        {
            if ((dzialanie != Dzialanie.nic) && (!rownanie))
            {
                switch(dzialanie)
                {
                    case Dodawanie: {
                        wA = wP.add(wJP).stripTrailingZeros();
                        wynikS = String.valueOf(wA.toPlainString());
                        wP = wA;
                        break;
                    }
                    case Odejmowanie: {
                        wA = wJP.subtract(wP).stripTrailingZeros();
                        wynikS = String.valueOf(wA.toPlainString());
                        wP = wA;
                        break;
                    }
                    case Mnozenie: {
                        wA = wJP.multiply(wP).stripTrailingZeros();
                        wynikS = String.valueOf(wA.toPlainString());
                        wP = wA;
                        break;
                    }
                    case Dzielenie: {
                        if (wA.compareTo(BigDecimal.valueOf(0)) != 0)
                        {
                            wA = wJP.divide(wP, 20, RoundingMode.HALF_UP).stripTrailingZeros();
                            wynikS = String.valueOf(wA.toPlainString());
                            wP = wA;
                        }
                        else blad = true;
                        break;
                    }
                }
            }

            if (!rownanie) {
                wP = wA;
                wJP = wP;
                wA = new BigDecimal("0");
            }
            else {
                wP = wJP;
                wJP = wA;
                wA = new BigDecimal("0");
            }

            switch (x)
            {
                case "+":
                    dzialanie = Dzialanie.Dodawanie;
                    break;
                case "-":
                    dzialanie = Dzialanie.Odejmowanie;
                    break;
                case "\u00D7":
                    dzialanie = Dzialanie.Mnozenie;
                    break;
                case "\u00F7":
                    dzialanie = Dzialanie.Dzielenie;
                    break;
            }
            odNowa = true;
            poprzecinku = false;
            przecinek = false;
            rownanie = false;
        }

        if (x.equals("\u221A"))
        {
            MathContext mc = new MathContext(20);
            wP = sqrt(wA, mc).stripTrailingZeros();
            wynikS = String.valueOf(wP.toPlainString());
            pierw = true;
            przecinek = false;
            poprzecinku=false;
            dzialanie = Dzialanie.nic;
        }

        if ((x.equals("=")) && (dzialanie != Dzialanie.nic))
        {
            odNowa = true;
            switch (dzialanie) {
                case Dodawanie: {
                    wA = wP.add(wJP).stripTrailingZeros();
                    wynikS = String.valueOf(wA.toPlainString());
                    wJP = wA;
                    break;
                }
                case Odejmowanie: {
                    wA = wJP.subtract(wP).stripTrailingZeros();
                    wynikS = String.valueOf(wA.toPlainString());
                    wJP = wA;
                    break;
                }
                case Mnozenie: {
                    wA = wJP.multiply(wP).stripTrailingZeros();
                    wynikS = String.valueOf(wA.toPlainString());
                    wJP = wA;
                    break;
                }
                case Dzielenie: {
                    //wA.stripTrailingZeros();
                    if (wA.compareTo(BigDecimal.valueOf(0)) != 0)
                    {
                        if (wJP.compareTo(BigDecimal.valueOf(0)) == 0) {
                            wA = wJP.divide(wP, 0, RoundingMode.HALF_UP).stripTrailingZeros();
                        } else wA = wJP.divide(wP, 20, RoundingMode.HALF_UP).stripTrailingZeros();

                        wynikS = String.valueOf(wA.toPlainString());
                        wJP = wA;
                    }
                    else blad = true;
                    break;
                }
            }

            rownanie=true;
            przecinek = false;
            poprzecinku = false;
        }

        if (!blad) wynik.setText(wynikS);
        else {
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast,
                    (ViewGroup) findViewById(R.id.custom_toast_container));

            TextView text = (TextView) layout.findViewById(R.id.text);
            //text.setText(R.string.komunikatBledu);

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();
        }
    }
}