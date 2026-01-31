package com.skhojkn.seekhojikan.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Data(

	@SerialName("title_japanese")
	val titleJapanese: String? = null,

	@SerialName("favorites")
	val favorites: Int? = null,

	@SerialName("broadcast")
	val broadcast: Broadcast? = null,

	@SerialName("year")
	val year: Int? = null,

	@SerialName("rating")
	val rating: String? = null,

	@SerialName("scored_by")
	val scoredBy: Int? = null,

	@SerialName("title_synonyms")
	val titleSynonyms: List<String?>? = null,

	@SerialName("source")
	val source: String? = null,

	@SerialName("title")
	val title: String? = null,

	@SerialName("type")
	val type: String? = null,

	@SerialName("trailer")
	val trailer: Trailer? = null,

	@SerialName("duration")
	val duration: String? = null,

	@SerialName("score")
	val score: Any? = null,

	@SerialName("themes")
	val themes: List<Any?>? = null,

	@SerialName("approved")
	val approved: Boolean? = null,

	@SerialName("genres")
	val genres: List<GenresItem?>? = null,

	@SerialName("popularity")
	val popularity: Int? = null,

	@SerialName("members")
	val members: Int? = null,

	@SerialName("title_english")
	val titleEnglish: String? = null,

	@SerialName("rank")
	val rank: Int? = null,

	@SerialName("season")
	val season: String? = null,

	@SerialName("airing")
	val airing: Boolean? = null,

	@SerialName("episodes")
	val episodes: Int? = null,

	@SerialName("aired")
	val aired: Aired? = null,

	@SerialName("images")
	val images: Images? = null,

	@SerialName("studios")
	val studios: List<StudiosItem?>? = null,

	@SerialName("mal_id")
	val malId: Int? = null,

	@SerialName("titles")
	val titles: List<TitlesItem?>? = null,

	@SerialName("synopsis")
	val synopsis: String? = null,

	@SerialName("explicit_genres")
	val explicitGenres: List<Any?>? = null,

	@SerialName("licensors")
	val licensors: List<Any?>? = null,

	@SerialName("url")
	val url: String? = null,

	@SerialName("producers")
	val producers: List<ProducersItem?>? = null,

	@SerialName("background")
	val background: String? = null,

	@SerialName("status")
	val status: String? = null,

	@SerialName("demographics")
	val demographics: List<DemographicsItem?>? = null
)