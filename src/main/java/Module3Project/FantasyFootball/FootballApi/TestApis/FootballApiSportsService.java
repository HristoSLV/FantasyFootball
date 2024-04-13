package Module3Project.FantasyFootball.FootballApi.TestApis;

import Module3Project.FantasyFootball.Player.*;
import Module3Project.FantasyFootball.Player.APIResponse.APIResponse;
import Module3Project.FantasyFootball.Player.APIResponse.PlayerResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class FootballApiSportsService {

    public static final String apiSportsUrl = "https://v3.football.api-sports.io/players?league=5&season=2020";
    public static final String apiSportsKey = "eb641cfe432da0486fd17b785543f13c";
    public static final String apiSportsHost = "v3.football.api-sports.io";

    private PlayerRepository playerRepository;
    private PlayerMapper playerMapper;

    public FootballApiSportsService(PlayerRepository playerRepository, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
    }

    //@Autowired
    //private RestTemplate restTemplate;

    public PlayersListDTO getAllPlayersApiSports() {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("X-RapidAPI-Key", apiSportsKey);
            httpHeaders.set("x-rapidapi-host", apiSportsHost);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<PlayersListDTO> playerDTOResponseEntity = restTemplate.exchange(apiSportsUrl, HttpMethod.GET, new HttpEntity<>(httpHeaders), PlayersListDTO.class);
            //ResponseEntity<APIResponse> apiResponseResponseEntity = restTemplate.exchange(apiSportsUrl, HttpMethod.GET, new HttpEntity<>(httpHeaders), APIResponse.class);

            /*RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.exchange(apiSportsUrl, HttpMethod.GET, new HttpEntity<>(httpHeaders), String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            APIResponse apiResponse = objectMapper.readValue(responseEntity.getBody(), APIResponse.class);

            List<PlayerResponse> playerResponses = apiResponse.getResponse();
            for (PlayerResponse playerResponse : playerResponses) {
                PlayerDTO playerDTO = playerResponse.getPlayerDTO();
            }*/

            return playerDTOResponseEntity.getBody();
        } catch (Exception e){
            throw new RuntimeException();
        }
    }


    public Object getAllPlayersApiSportsTest() {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("X-RapidAPI-Key", apiSportsKey);
            httpHeaders.set("x-rapidapi-host", apiSportsHost);


            //RestTemplate restTemplate = new RestTemplate();
            //ResponseEntity<PlayersListDTO> playerDTOResponseEntity = restTemplate.exchange(apiSportsUrl, HttpMethod.GET, new HttpEntity<>(httpHeaders), PlayersListDTO.class);
            //ResponseEntity<APIResponse> apiResponseResponseEntity = restTemplate.exchange(apiSportsUrl, HttpMethod.GET, new HttpEntity<>(httpHeaders), APIResponse.class);


            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.exchange(apiSportsUrl, HttpMethod.GET, new HttpEntity<>(httpHeaders), String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            APIResponse apiResponse = objectMapper.readValue(responseEntity.getBody(), APIResponse.class);

            List<PlayerResponse> playerResponses = apiResponse.getResponse();
            for (PlayerResponse playerResponse : playerResponses) {
                PlayerDTO playerDTO = playerResponse.getPlayerDTO();
            }

            return apiResponse;
        } catch (Exception e){
            throw new RuntimeException();
        }
    }

    public ResponseEntity getAllPlayersApiSportsTest2() {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("X-RapidAPI-Key", apiSportsKey);
            httpHeaders.set("x-rapidapi-host", apiSportsHost);

            RestTemplate restTemplate = new RestTemplate();
            //ResponseEntity<PlayersListDTO> playersListDTOResponseEntity = restTemplate.exchange(apiSportsUrl, HttpMethod.GET, new HttpEntity<>(httpHeaders), PlayersListDTO.class);

            ResponseEntity<String> stringResponseEntity = restTemplate.exchange(apiSportsUrl, HttpMethod.GET, new HttpEntity<>(httpHeaders), String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            APIResponse apiResponse = objectMapper.readValue(stringResponseEntity.getBody(), APIResponse.class);

            List<PlayerResponse> playerResponses = apiResponse.getResponse();
            for (PlayerResponse playerResponse : playerResponses) {
                PlayerDTO playerDTO = playerResponse.getPlayerDTO();
                //playerDTO.setName(playerResponse.getPlayerDTO().getName());
                //String name = playerResponse.getPlayerDTO().getName();
            }

            return stringResponseEntity;
        } catch (Exception e){
            throw new RuntimeException();
        }
    }

    public ResponseEntity getAllPlayersApiSportsTest3() {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("X-RapidAPI-Key", "eb641cfe432da0486fd17b785543f13c");
            httpHeaders.set("x-rapidapi-host", "v3.football.api-sports.io");

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> stringResponseEntity = restTemplate.exchange("https://v3.football.api-sports.io/players?league=5&season=2020", HttpMethod.GET, new HttpEntity<>(httpHeaders), String.class);

            return stringResponseEntity;
        } catch (Exception e){
            throw new RuntimeException();
        }
    }

    public void parseJsonAndExtractPlayers(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(json);

        JsonNode get = rootNode.path("get");
        JsonNode response = rootNode.path("response");

        if (response.isArray()) {
            for (JsonNode resNode : response) {
                JsonNode players = resNode.path("players");
                if (players.isObject()) {
                    JsonNode player = players.path("player");
                    // Process your player data here...
                    System.out.println(player);
                }
            }
        }
    }
}
