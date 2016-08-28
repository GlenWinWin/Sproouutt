package com.myapp.glenwin.sproouut;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListWithPictures extends ListActivity {
    private Integer integer;
    int[] drawablesAnimals = {R.drawable.animalone, R.drawable.animalstwo, R.drawable.animalthree, R.drawable.animalsfour,
            R.drawable.animalfive, R.drawable.animalsix, R.drawable.animalseven, R.drawable.animaleight, R.drawable.animalnine,
            R.drawable.animalsten};
    String[] nameAnimals = {"Mindoro Crocodile", "Mindoro Bleeding Heart", "Rufous-Headed Hornbill", "Hawksbill Sea Turtle", "Red-vented Cockatoo"
            , "Philippine Forest Turtle","Philippine Naked-Backed Fruitbat",
            "Visayan Warty Pig", "Tamaraw", "Philippine Eagle"};
    String[] stausAnimals = {"Endangered", "Endangered", "Endemic", "Endangered", "Endangered"
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




    ListView myListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_with_pictures);

        CustomAdapter adapter = new CustomAdapter(this, nameAnimals, drawablesAnimals);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Integer integer = 0;

        Intent intent = new Intent(getApplication(), SubDetails.class);
        intent.putExtra(integer.toString(), "positions");
        startActivity(intent);






    }
}