package com.example.alejandro.esmus.model;

import com.example.alejandro.esmus.connection.RestClient;
import com.example.alejandro.esmus.presentation.Content;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Miren on 08/01/2016.
 */
public class Bussines  {


    private RestClient rest;


    String jsonArray = "[{\"nombre\":\"Universidad\",\"prep\":\"en la\",\"subtemas\":[{\"nombre\":\"Profesores\",\"prep\":\"Con los\",\"frases\":[{\"fr\":\"Me preguntaba si podría explicar....de nuevo\",\"tr\":\"I was wondering if you could explain...again\",\"path\":null,\"pathG\":null},{\"fr\":\"Nosotros querríamos saber dónde va a ser la siguiente clase\",\"tr\":\"We wanted to know where the next lesson is going to be\",\"path\":null,\"pathG\":null},{\"fr\":\"Yo estaría interesado/a en tomar parte en...\",\"tr\":\"I would be interested to take part in...\",\"path\":null,\"pathG\":null},{\"fr\":\"¿Podríamos reunirnos el (día de la semana) para hablar de...?\",\"tr\":\"Could we have a meeting on (day of the week) to talk about...?\",\"path\":null,\"pathG\":null}]},{\"nombre\":\"Compañeros\",\"prep\":\"Con los\",\"frases\":[{\"fr\":\"¿Qué hay/tal?\",\"tr\":\"What´s up?\",\"path\":null,\"pathG\":null},{\"fr\":\"¿Qué pasa?\",\"tr\":\"What´s the crack?\",\"path\":null,\"pathG\":null},{\"fr\":\"¡Él/ella es genial!\",\"tr\":\"He´s /she´s scream!\",\"path\":null,\"pathG\":null},{\"fr\":\"¡Gracias, colega!\",\"tr\":\"Cheers, mate!\",\"path\":null,\"pathG\":null},{\"fr\":\"¡Madre mía!\",\"tr\":\"My goodness!\",\"path\":null,\"pathG\":null},{\"fr\":\"¡Venga ya!\",\"tr\":\"Do me a favour!\",\"path\":null,\"pathG\":null},{\"fr\":\"Me voy\",\"tr\":\"I´m off\",\"path\":null,\"pathG\":null},{\"fr\":\"Yo tambíén\",\"tr\":\"Same here\",\"path\":null,\"pathG\":null},{\"fr\":\"¡Qué pena!\",\"tr\":\"What a pitty!\",\"path\":null,\"pathG\":null},{\"fr\":\"Me interesa/gusta Charles, Cloe... (el nombre de la persona).\",\"tr\":\"I am into Charles, Cloe...\",\"path\":null,\"pathG\":null},{\"fr\":\"Me vuelve loco/a Charles, Cloe... (el nombre de la persona).\",\"tr\":\"I am crazy about Charles, Cloe...\",\"path\":null,\"pathG\":null},{\"fr\":\"¡No te metas en mi vida!?\",\"tr\":\"Mind your own business!\",\"path\":null,\"pathG\":null},{\"fr\":\"¡Déjame en paz!\",\"tr\":\"Give me a break!\",\"path\":null,\"pathG\":null}]},{\"nombre\":\"Secretaria\",\"prep\":\"En\",\"frases\":[{\"fr\":\"¿Me podría decir algo más sobre...?\",\"tr\":\"Could you tell me something more about...?\",\"path\":null,\"pathG\":null},{\"fr\":\"¿Me podría dar más detalles a cerca de...?\",\"tr\":\"Could you give me more details about...?\",\"path\":null,\"pathG\":null},{\"fr\":\"¿Le importaría decirme...?\",\"tr\":\"Would you mind telling me...?\",\"path\":null,\"pathG\":null},{\"fr\":\"Yo he tenido algunos problemas con mi beca, ¿podría ayudarme, por favor?\",\"tr\":\"I have had some problems with my eductional grant, could you help me, please?\",\"path\":null,\"pathG\":null},{\"fr\":\"¿Podría decirme cuáles son los impresos de la beca?\",\"tr\":\"Could you tell me what are grant´s application forms?\",\"path\":null,\"pathG\":null},{\"fr\":\"¿Podría decirme cuál es el programa de becas?\",\"tr\":\"Could you tell me what is the scholarship programme?\",\"path\":null,\"pathG\":null}]}]},{\"nombre\":\"Compras\",\"prep\":\"de\",\"subtemas\":[{\"nombre\":\"Peluqueria\",\"prep\":\"En la \",\"frases\":[{\"fr\":\"Alisar\",\"tr\":\"Straighten\",\"path\":null,\"pathG\":null},{\"fr\":\"Ondular\",\"tr\":\"Wave\",\"path\":null,\"pathG\":null},{\"fr\":\"Rizar\",\"tr\":\"Curl\",\"path\":null,\"pathG\":null},{\"fr\":\"Flequillo\",\"tr\":\"Fringe\",\"path\":null,\"pathG\":null},{\"fr\":\"Mid-length\",\"tr\":\"Mid-length\",\"path\":null,\"pathG\":null},{\"fr\":\"Cortar las puntas\",\"tr\":\"Trim ends\",\"path\":null,\"pathG\":null}]},{\"nombre\":\"Tiendas de ropa\",\"prep\":\"En las\",\"frases\":[{\"fr\":\"¿Me lo puedo probar?\",\"tr\":\"Can I try it on?\",\"path\":null,\"pathG\":null},{\"fr\":\"¿Puedo pagar con tarjeta?\",\"tr\":\"Can I pay by credit card?\",\"path\":null,\"pathG\":null},{\"fr\":\"¿Tenéis alguno más pequeño/grande?\",\"tr\":\"Have you got something smaller/bigger?\",\"path\":null,\"pathG\":null},{\"fr\":\"¿Tenéis esto en otro color?\",\"tr\":\"Do you have another color for this?\",\"path\":null,\"pathG\":null},{\"fr\":\"Quiero devolver esto\",\"tr\":\"I wanna to take this back\",\"path\":null,\"pathG\":null},{\"fr\":\"¿Puedo tener mi dinero de vuelta?\",\"tr\":\"Could I have a refund?\",\"path\":null,\"pathG\":null},{\"fr\":\"Me gustaría cambiar esto por otra talla\",\"tr\":\"I'd like to change this for a different size\",\"path\":null,\"pathG\":null},{\"fr\":\" Estoy solo echando un vistazo, gracias!\",\"tr\":\"I´m just taking a look, thak you\",\"path\":null,\"pathG\":null},{\"fr\":\" ¿Me podría decir dónde están los probadores, por favor?\",\"tr\":\"Could you please tell me were the changing rooms are?\",\"path\":null,\"pathG\":null}]},{\"nombre\":\"Supermercado\",\"prep\":\"En el\",\"frases\":[{\"fr\":\"¿A qué horas abrís?\",\"tr\":\"What times are you open?\",\"path\":null,\"pathG\":null},{\"fr\":\"Estoy buscando...\",\"tr\":\"I am looking for...\",\"path\":null,\"pathG\":null},{\"fr\":\"¿Dónde puedo encontrar...?\",\"tr\":\"Where can I find...?\",\"path\":null,\"pathG\":null},{\"fr\":\"Me llevo esto\",\"tr\":\"I´ll take this\",\"path\":null,\"pathG\":null}]}]},{\"nombre\":\"Fiesta\",\"prep\":\"de\",\"subtemas\":[{\"nombre\":\"Bebidas\",\"prep\":\"Pidiendo\",\"frases\":[{\"fr\":\"Tres chupitos de tequila, ¡por favor!\",\"tr\":\"Three shots of tequila, please!\",\"path\":null,\"pathG\":null},{\"fr\":\"¡Quédate el cambio!\",\"tr\":\"Keep the change!\",\"path\":null,\"pathG\":null},{\"fr\":\"¡Ponme otro de estos!\",\"tr\":\"Same again\",\"path\":null,\"pathG\":null},{\"fr\":\"¡Última ronda!\",\"tr\":\"Last orders!\",\"path\":null,\"pathG\":null},{\"fr\":\"¡Tomaré esto!\",\"tr\":\"I´ll get these!\",\"path\":null,\"pathG\":null}]},{\"nombre\":\"Ligar\",\"prep\":\"Para\",\"frases\":[{\"fr\":\"Invito yo\",\"tr\":\"It´s on me\",\"path\":null,\"pathG\":null},{\"fr\":\"¿Quieres beber algo?\",\"tr\":\"Can I get a drink?\",\"path\":null,\"pathG\":null},{\"fr\":\"¿Estás solo/a?\",\"tr\":\"Are you on your own?\",\"path\":null,\"pathG\":null},{\"fr\":\"¡Dime si quieres quedar algún día!\",\"tr\":\"If you´d like to meet up sometime, let me know!\",\"path\":null,\"pathG\":null},{\"fr\":\"¿Me das tu número?\",\"tr\":\"Would you give me your phone number?\",\"path\":null,\"pathG\":null},{\"fr\":\"Perdona, pero me debes un cubata, porque el mío se me ha caído cuando te he visto\",\"tr\":\"Sorry but you owe me a drink  because when I saw you, I dropped mine\",\"path\":null,\"pathG\":null},{\"fr\":\"Me apuesto 20$ a que me dices que no.\",\"tr\":\"I´l bet you 20$ that you are going to say “no” to me\",\"path\":null,\"pathG\":null},{\"fr\":\"Vamos a dar una vuelta\",\"tr\":\"Let´s take a ride\",\"path\":null,\"pathG\":null}]},{\"nombre\":\"Casa\",\"prep\":\"Volver\",\"frases\":[{\"fr\":\"Yo me voy ya\",\"tr\":\"I´m outta here\",\"path\":null,\"pathG\":null},{\"fr\":\"¿Nos vamos?\",\"tr\":\"Shall we hit the road?\",\"path\":null,\"pathG\":null},{\"fr\":\"Perdone, Quiero ir a la calle Bridge, ¿voy bien?\",\"tr\":\"Excuse me, I want to go to Bridge Street, is this the right way?\",\"path\":null,\"pathG\":null},{\"fr\":\"Buenas noches, ¿podría venir un taxi a la calle Bridge?\",\"tr\":\"Good evening, may I have a taxi at Bridge Street?\",\"path\":null,\"pathG\":null},{\"fr\":\"¿Podría llevarme a la calle Bridge?\",\"tr\":\"Could you take me to Bridge Street?\",\"path\":null,\"pathG\":null},{\"fr\":\"¡Ve más deprisa, por favor\",\"tr\":\"Please, step on it!\",\"path\":null,\"pathG\":null},{\"fr\":\"¿Cuánto es?\",\"tr\":\"How much is the fare?\",\"path\":null,\"pathG\":null}]}]},{\"nombre\":\"Buscar piso\",\"prep\":\"Para\",\"subtemas\":[{\"nombre\":\"Necesidades\",\"prep\":\"Explicar\",\"frases\":[{\"fr\":\"Necesito una casa cerca de la universidad\",\"tr\":\"I need a house close to the University\",\"path\":null,\"pathG\":null},{\"fr\":\"No me importa si...\",\"tr\":\"I don´t mind if I have to...\",\"path\":null,\"pathG\":null},{\"fr\":\"Necesito como mínimo...\",\"tr\":\"I need at least...\",\"path\":null,\"pathG\":null},{\"fr\":\"No quiero...\",\"tr\":\"I don´t want to...\",\"path\":null,\"pathG\":null}]},{\"nombre\":\"Dudas\",\"prep\":\"Preguntar\",\"frases\":[{\"fr\":\"¿Cuál es la dirección?\",\"tr\":\"What is the adress?\",\"path\":null,\"pathG\":null},{\"fr\":\"¿Qué incluye el precio del alquiler?\",\"tr\":\"What does the price of the rent include?\",\"path\":null,\"pathG\":null},{\"fr\":\" ¿Están dados de alta los suministros de agua, luz y/o gas?\",\"tr\":\"Are there the supplies of water, ligth and gas given of discharge?\",\"path\":null,\"pathG\":null},{\"fr\":\"¿Tengo que hacerme yo cargo de las averías?\",\"tr\":\"Do I have to take charge of the breakdowns?\",\"path\":null,\"pathG\":null},{\"fr\":\"¿Está amueblada la casa?\",\"tr\":\"Is the house furnished?\",\"path\":null,\"pathG\":null},{\"fr\":\"¿Puedo tener mascotas?\",\"tr\":\"Is it possible for me to have pets?\",\"path\":null,\"pathG\":null}]}]}]";


    public Bussines( RestClient rest){
        this.rest=rest;

    }

    public JSONArray getJson(String relativePath) throws IOException {

        JSONArray contenido=new JSONArray();
        try {

            contenido= new JSONArray(rest.getJson(relativePath).toString());


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return contenido;

    }

    public byte[] getAudio(String relativePath){


        return rest.getAudio(relativePath);
    }


    public boolean isExist( String path){

        boolean aux=false;
        File file=new File(path);

        if(file.exists()){

                aux = true; //existe ya el archivo
                Log.i("esmus","camiando a true el aux de isExist");


        }


        return  aux;
    }




}
