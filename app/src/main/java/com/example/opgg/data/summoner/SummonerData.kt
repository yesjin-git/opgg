package com.example.opgg.data.summoner

/**
 * Summoner's info which holds leagues results
 *
 * ex)
 * {
 *  "summoner":{
 *    "name":"genetory",
 *    "level":74,
 *    "profileImageUrl":"https://opgg-static.akamaized.net/images/profile_icons/profileIcon1625.jpg",
 *    "profileBorderImageUrl":"https://opgg-static.akamaized.net/images/borders2/challenger.png",
 *    "url":"https://www.op.gg/summoner/userName=genetory",
 *    "leagues":[
 *      {
 *        "hasResults":true,
 *        "wins":306,
 *        "losses":397,
 *        "tierRank":{
 *        "name":"솔랭",
 *        "tier":"Challenger",
 *        "tierDivision":"Challenger",
 *        "string":"Challenger (747LP)",
 *        "shortString":"C1",
 *        "division":"i",
 *        "imageUrl":"https://opgg-static.akamaized.net/images/medals/challenger_1.png",
 *        "lp":747,
 *        "tierRankPoint":32
 *        }
 *      }
 *    ],
 *    "profileBackgroundImageUrl":"http://ddragon.leagueoflegends.com/cdn/img/champion/splash/Taliyah_0.jpg"
 *  }
 *}
 */
class Genetory {
    data class Genetory(
        val summoner: SummonerInfo
    )

    data class SummonerInfo(
        val name: String,
        val level: Int,
        val profileImageUrl: String,
        val profileBorderImageUrl: String,
        val url: String,
        val leagues: List<Leagues>,
        val profileBackgroundImageUrl: String
    )

    data class Leagues(
        val hasResult: Boolean,
        val wins: Int,
        val losses: Int,
        val tierRank: TierRank
    )

    /**
     * @param name Match category ex) 솔랭
     * @param string Tier title with lp ex) Challenger (747LP)
     */
    data class TierRank(
        val name: String,
        val tier: String,
        val tierDivision: String,
        /**
         * Tier title with lp ex) Challenger (747LP)
         */
        val string: String,
        val shortString: String,
        val division: String,
        val imageUrl: String,
        val lp: Int,
        val tierRankPoint: Int
    )
}

/**
 *  Match history
 *
 *  ex)
 *  {
 *    "games":[
 *      {
 *        "mmr":3730,
 *        "champion":{
 *          "imageUrl":"https://opgg-static.akamaized.net/images/lol/champion/Jayce.png",
 *          "level":12
 *        },
 *        "spells":[
 *          {
 *            "imageUrl":"https://opgg-static.akamaized.net/images/lol/spell/SummonerTeleport.png"
 *          }
 *        ],
 *        "items":[
 *          {
 *            "imageUrl":"https://opgg-static.akamaized.net/images/lol/item/1056.png"
 *          }
 *        ],
 *        "needRenew":false,
 *        "gameId":"261288175",
 *        "createDate":1571654629,
 *        "gameLength":523,
 *        "gameType":"Ranked Solo",
 *        "summonerId":"13696133",
 *        "summonerName":"genetory",
 *        "tierRankShort":"C1",
 *        "stats":{
 *          "general":{
 *            "kill":8,
 *            "death":6,
 *            "assist":1,
 *            "kdaString":"1.50:1",
 *            "cs":157,
 *            "csPerMin":7.5,
 *            "contributionForKillRate":"31%",
 *            "goldEarned":3632,
 *            "totalDamageDealtToChampions":4108,
 *            "largestMultiKillString":"",
 *            "opScoreBadge":"ACE"
 *        },
 *        "ward":{
 *          "sightWardsBought":0,
 *          "visionWardsBought":2
 *    }
 *      },
 *        "mapInfo":null,
 *        "peak":[
 *          "https://opgg-static.akamaized.net/images/lol/perk/8229.png"
 *        ],
 *        "isWin":true
 *      }
 *    ],
 *    "champions":[
 *      {
 *        "name":"Viktor",
 *        "imageUrl":"https://opgg-static.akamaized.net/images/lol/champion/Viktor.png?image=w_30&v=1",
 *        "games":13,
 *        "kills":15,
 *        "deaths":15,
 *        "assists":17,
 *        "wins":4,
 *        "losses":9
 *      }
 *    ],
 *    "positions":[
 *      {
 *        "games":20,
 *        "wins":3,
 *        "losses":17,
 *        "position":"ADC",
 *        "positionName":"Bottom"
 *      }
 *    ]
 *  }
 */
class MatchData {

    data class Match(
        val games: List<Game>,
        val champions: List<Champion>,
        val positions: List<Position>
    )

    data class Game(
        val mmr: Int,
        val champion: GameChampion,
        val spells: List<Spell>,
        val items: List<Item>,
        val needRenew: Boolean,
        val gameId: String,
        val createDate: Int,
        val gameLength: Int,
        val gameType: String,
        val summonerId: String,
        val summonerName: String,
        val tierRankShort: String,
        val stats: Stats,
        val mapInfo: String?,
        val peak: List<String>,
        val isWin: Boolean
    )

    data class GameChampion(
        val imageUrl: String,
        val level: Int
    )

    data class Spell(
        val imageUrl: String
    )

    data class Item(
        val imageUrl: String
    )


    data class Champion(
        val name: String,
        val imageUrl: String,
        val games: Int,
        val kills: Int,
        val deaths: Int,
        val assists: Int,
        val wins: Int,
        val losses: Int
    )

    data class Stats(
        val general: General,
        val ward: Ward
    )

    data class General(
        val kill: Int,
        val deaths: Int,
        val assist: Int,
        val kdaString: String,
        val cs: Int,
        val csPerMin: Int,
        val contributionForKillRate: String,
        val goldEarned: Int,
        val totalDamageDealtToChampions: Int,
        val largestMultiKillString: String,
        val opScoreBadge: String
    )

    data class Ward(
        val sightWardBought: Int,
        val visionWardBought: Int
    )

    /**
     * @param position ex) ADC
     * @param positionName ex) Bottom
     */
    data class Position(
        val games: Int,
        val wins: Int,
        val losses: Int,
        val position: String,
        val positionName: String
    )
}
