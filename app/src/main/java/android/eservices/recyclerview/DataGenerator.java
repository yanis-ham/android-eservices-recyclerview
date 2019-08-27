package android.eservices.recyclerview;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

    public static List<GameViewModel> generateData() {
        List<GameViewModel> gameViewModelList = new ArrayList<>();

        gameViewModelList.add(new GameViewModel("Borderlands 2", "Sorti en 2012", "https://images-na.ssl-images-amazon.com/images/I/71JEQvLcYqL._SX466_.jpg"));
        gameViewModelList.add(new GameViewModel("CS GO", "Sorti en 2012", "https://mmo.it/wp-content/uploads/2016/04/CSGO-Copertina-940x300.png"));
        gameViewModelList.add(new GameViewModel("Dota 2", "Sorti en 2013", "https://steamcdn-a.akamaihd.net/steam/apps/570/header.jpg?t=1561594389"));
        gameViewModelList.add(new GameViewModel("Team Fortress 2", "Sorti en 2007", "https://o.aolcdn.com/images/dims?quality=85&image_uri=http%3A%2F%2Fo.aolcdn.com%2Fhss%2Fstorage%2Fmidas%2Fdedd8c573430c9aba0478c0a8d8f3b1f%2F204049298%2Fbx32wbpuape1okkdjuso-ed.jpg&client=amp-blogside-v2&signature=e346116ae5eeb29856df1b806ec33afdaefc5744"));
        gameViewModelList.add(new GameViewModel("Rocket League", "Sorti en 2015", "https://rocketleague.media.zestyio.com/rl_platform_keyart_2019.309bf22bd29c2e411e9dd8eb07575bb1.jpg"));
        gameViewModelList.add(new GameViewModel("Civilization V", "Sorti en 2016", "https://hb.imgix.net/775d06b12cdafc76142869870e04e51fee5487ec.jpg?auto=compress,format&fit=crop&h=353&w=616&s=1920520e033fef186e599bac32272fda"));
        gameViewModelList.add(new GameViewModel("Age of Empire II", "Sorti en 2013", "https://www.numerama.com/content/uploads/2019/06/age-of-empire-ii-remastered.jpg"));
        return gameViewModelList;
    }
}
