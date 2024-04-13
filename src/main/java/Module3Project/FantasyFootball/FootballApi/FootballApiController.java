package Module3Project.FantasyFootball.FootballApi;

import Module3Project.FantasyFootball.FootballApi.TestApis.FootballApiSportsService;
import Module3Project.FantasyFootball.FootballApi.TestApis.FootballRapidApiService;
import Module3Project.FantasyFootball.Player.APIResponse.APIResponse;
import Module3Project.FantasyFootball.Player.PlayerDTO;
import Module3Project.FantasyFootball.Player.PlayersListDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@RestController
//@RequestMapping("/api")
public class FootballApiController {

    private FootballRapidApiService footballRapidApiService;
    private FootballApiSportsService apiSportsService;

    public FootballApiController(FootballRapidApiService footballRapidApiService, FootballApiSportsService apiSportsService) {
        this.footballRapidApiService = footballRapidApiService;
        this.apiSportsService = apiSportsService;
    }

    @GetMapping("/api/football")
    public String getPlayersFromApi() {
        String url = "https://v3.football.api-sports.io/players?season=2018&league=61";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);

        return result;
    }


    @GetMapping("/api/token")
    public String getApiToken() {
        String url = "https://v3.football.api-sports.io/status";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);

        return result;
    }

    /*@GetMapping("/api/rapidapi")
    public PlayerDTO getRapidApi() {
        return footballRapidApiService.getAllPlayersRapidApi();
    }*/

    @GetMapping("/api/api-sports")
    public PlayersListDTO getApiSports() {
        return apiSportsService.getAllPlayersApiSports();
    }

    @GetMapping("/api/api-sports2")
    public void parseJsonAndExtractPlayers(String json) throws IOException {
        apiSportsService.parseJsonAndExtractPlayers(json);
    }

    @GetMapping("/api/api-sports3")
    public Object getApiSportsTest() {
        return apiSportsService.getAllPlayersApiSportsTest();
    }

    @GetMapping("/api/api-sports4")
    public ResponseEntity getApiSportsTest2() {
        return apiSportsService.getAllPlayersApiSportsTest2();
    }

    @GetMapping("/api/api-sports5")
    public List<PlayerDTO> getApiSportsTest3() {
        return apiSportsService.getAllPlayersCMDLineMain();
    }
}
