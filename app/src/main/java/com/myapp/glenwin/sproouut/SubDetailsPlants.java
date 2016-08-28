package com.myapp.glenwin.sproouut;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class SubDetailsPlants extends AppCompatActivity {

    TextView NameID, StatusID, DescriptionID, ScientificNameID, PlaceID;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_details);

        imageView = (ImageView)findViewById(R.id.subDeatilsId);
        NameID = (TextView)findViewById(R.id.nameId);
        StatusID = (TextView)findViewById(R.id.statusId);
        DescriptionID = (TextView)findViewById(R.id.descriptionId);
        ScientificNameID = (TextView)findViewById(R.id.scientificNameId);
        PlaceID = (TextView)findViewById(R.id.placeId);

        int[] drawablesPlant = {R.drawable.balayog, R.drawable.balitbitan, R.drawable.banuyo, R.drawable.bayagusa, R.drawable.lamog,
                R.drawable.malakmalak, R.drawable.pili,  R.drawable.potat, R.drawable.santiki,
                R.drawable.tamayuan,  R.drawable.toog};
        String[] namePlant = {"Balayong", "Balibitan", "Banuyo", "Lamog",
                "Malak-malak", "Pili",  "Potat", "Santiki Paitan", "Tamayuan", "Toog"};

        String[] statusPlant = {"Endemic", "Endangered", "Endangered", "Endangered", "Endangered"
                , "Endemic","Endemic",
                "Endemic", "Endangered", "Endangered"};
        String[] placePlant = {"Philippines","Luzon and Visayas", "Philippines", "Philippines", "Philippines", "Luzon, Samar and Palawan", "Philippines", "Luzon and Palawan", "Philippines", "Mindanao"};
        String[] scientificName = {"Afzelia rhomboides", "Afzelia rhomboides", "Wallaceodendron celebicum","Planchonia spectabilis","Palaquium philippense",
                "Canarium ovatum","Barringtonia racemosa ","Cleidion spiciflorum","Strombosia philippinensis","Combretodendron spectabilis"};

        String[] descripTionAnimals = {"Tree reaching up to 25m in height and 50cm in diameter.",
                "A much branched, small tree growing 4m – 26m tall. 1-2 pairs of leathery, inequilateral, 10cm leaves. Pale yellow leaves are in drooping tassels. Flowers are whitish yellow and with large silvery bracts.",
                " A tree growing up to 25m high and 100cm in diameter. Outer bark has leaf scar mark and leaves are bipinnate; 3-5 pairs of pinnae.",
                "A large tree growing up to 20m in height and a diameter of 60cm. Bark is dark brown and slightly flaky. Leaves turn red when mature, flowers in February-April.",
                "A large-sized tree growing up to 25m high and 80cm in diameter. Bark is smooth and brownish; reddish brown inner bark and exudes white sap. "
                ,"A tree growing up to 20m high and 40cm in diameter. Bark is brown with large flakes, exudes white resin. Leaves are compound, alternate with leaflets 16cm long with a round base.",
                "A straggling tree reaching a height of 10m or more. Inflorescence racemes are drooping, 20-60cm long. Flowers are white or pink. Fruit ovoid-oblong, 5-7cm by 3-4cm.",
                "A tree growing up to 15m tall. with a grey, smooth bark.  Flowers are unisexual, fruits are bilobed or trilobed capsules.",
                "A large tree reaching 30m in height and a diameter of 70cm. The trunk is straight and unbuttressed, while the crown is dense and compact. ",
                "A large tree reaching 40m in height and a diameter of 150cm. Bark is dark brown and flaky. Leaves are simple, obovate, 10-16cm L x 5-7.5cm broad."};

        Integer position = getIntent().getExtras().getInt("positions");
        try {

            NameID.setText(namePlant[position]);
            StatusID.setText(statusPlant[position]);
            DescriptionID.setText(descripTionAnimals[position]);
            PlaceID.setText(placePlant[position]);
            ScientificNameID.setText(scientificName[position]);
            imageView.setImageResource(drawablesPlant[position]);

        }catch (NumberFormatException nfe){
            Toast.makeText(this, "Doesn't Match", Toast.LENGTH_SHORT).show();
        }


    }
}
