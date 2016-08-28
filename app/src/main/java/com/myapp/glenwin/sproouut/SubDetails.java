package com.myapp.glenwin.sproouut;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class SubDetails extends AppCompatActivity {

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

        int[] drawablesAnimals = {R.drawable.animalone, R.drawable.animalstwo, R.drawable.animalthree, R.drawable.animalsfour,
                R.drawable.animalfive, R.drawable.animalsix, R.drawable.animalseven, R.drawable.animaleight, R.drawable.animalnine,
                R.drawable.animalsten};
        String[] nameAnimals = {"Mindoro Crocodile", "Mindoro Bleeding Heart", "Rufous-Headed Hornbill", "Hawksbill Sea Turtle", "Red-vented Cockatoo"
                , "Philippine Forest Turtle","Philippine Naked-Backed Fruitbat",
                "Visayan Warty Pig", "Tamaraw", "Philippine Eagle"};

        String[] statusAnimals = {"Endangered", "Endangered", "Endemic", "Endangered", "Endangered"
                , "Endemic","Endemic",
                "Endangered", "Endemic", "Endemic"};
        String[] placeAnimals = {"Mindoro","Mindoro", "Panay", "Global", "Palawan", "Palawan", "Palawan", "Cebu", "Mindoro", "Philippines"};
        String[] scientificName = {"Crocodylusmindorensis", "Gallicolumbaplatenae", "Aceroswaldeni","Eretmochelysimbricata","Cacatuahaematuropygia",
                "Siebenrockiellaleytensis","Dobsoniachapmani ","Suscebifrons","Bubalusmindorensis","Pithecophagajefferyi"};

        String[] descripTionAnimals = {"It is a relatively small, freshwater crocodile.",
                "Is a bird native to the Philippines. It is an endangered species.",
                "60-65 cm. Medium-sized, forest-dwelling hornbill. Male has rufous head, neck and upper breast.",
                "Hawksbill turtles are found throughout the tropical waters of the Atlantic, Pacific, and Indian Oceans. ",
                "Sometimes called the Philippine cockatoo or kalangay, is a critically endangered species of cockatoo that is endemic to the Philippines."
                ,"Philippine forest turtles have brown to reddish brown to black carapaces that reach a length of 21 cm (8.3 in).",
                "is a megabat that mostly lives on Negros Island. Two small populations were also found on Cebu Island in the Philippines.",
                "The Visayan warty pig is endemic to six islands in the Philippines. It is now extinct on four of the islands.",
                "The tamaraw was originally found all over Mindoro, from sea level up to the mountains (2000 meters above sea level) ",
                "also known as the monkey-eating eagle or great Philippine eagle, is an eagle of the family Accipitridae endemic to forests in the Philippines."};

            Integer position = getIntent().getExtras().getInt("position");
        try {


            NameID.setText(nameAnimals[position]);
            StatusID.setText(statusAnimals[position]);
            DescriptionID.setText(descripTionAnimals[position]);
            PlaceID.setText(placeAnimals[position]);
            ScientificNameID.setText(scientificName[position]);
            imageView.setImageResource(drawablesAnimals[position]);

        }catch (NumberFormatException nfe){
            Toast.makeText(this, "Doesn't Match", Toast.LENGTH_SHORT).show();
        }


    }
}
