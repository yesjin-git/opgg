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