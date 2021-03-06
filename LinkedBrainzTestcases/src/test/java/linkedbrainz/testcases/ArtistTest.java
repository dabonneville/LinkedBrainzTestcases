package linkedbrainz.testcases;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import junit.framework.JUnit4TestAdapter;
import linkedbrainz.testcases.model.Condition;
import linkedbrainz.testcases.model.TestResult;
import linkedbrainz.testcases.model.URICondition;

import org.junit.Test;

/**
 * 
 * @author zazi
 * 
 */
public class ArtistTest
{

	/**
	 * Fetches 5 music artists from the DB and resolves them via a SPARQL query.
	 * 
	 */
	@Test
	public void checkMusicArtists()
	{
		TestResult testResult = Utils.getInstance().checkClassViaGUIDSimple(
				"artist", "gid", "mo:MusicArtist", "ArtistsCheck");

		assertTrue(testResult.getFailMsg(), testResult.isSucceeded());
	}

	/**
	 * Fetches 5 solo music artists from the DB and resolves them via a SPARQL
	 * query.
	 * 
	 */
	@Test
	public void checkSoloMusicArtists()
	{
		TestResult testResult = Utils.getInstance()
				.checkClassViaGUIDAndCondition("artist", "gid", "type", "1",
						"mo:SoloMusicArtist", "SoloArtistsCheck");

		assertTrue(testResult.getFailMsg(), testResult.isSucceeded());
	}

	/**
	 * Fetches 5 music groups from the DB and resolves them via a SPARQL query.
	 * 
	 */
	@Test
	public void checkMusicGroups()
	{
		TestResult testResult = Utils.getInstance()
				.checkClassViaGUIDAndCondition("artist", "gid", "type", "2",
						"mo:MusicGroup", "GroupsCheck");

		assertTrue(testResult.getFailMsg(), testResult.isSucceeded());
	}

	/**
	 * Fetches 5 music artists from the DB and resolves their names against the
	 * result of the related SPARQL query.
	 * 
	 */
	@Test
	public void checkMusicArtistNames()
	{
		ArrayList<String> classTables = new ArrayList<String>();
		ArrayList<String> classTableRows = new ArrayList<String>();

		classTables.add("artist");
		classTables.add("artist_name");

		classTableRows.add("name");
		classTableRows.add("name");

		// add The Beatles as proof GUID
		TestResult testResult = Utils.getInstance()
				.checkSimplePropertyViaGUIDOnTheLeft(classTables,
						classTableRows, "mo:MusicArtist", "foaf:name", "name",
						1, 5, false, false, null,
						"b10bbbfc-cf9e-42e0-be17-e2c3e1d2600d",
						"ArtistNamesCheck");

		assertTrue(testResult.getFailMsg(), testResult.isSucceeded());
	}

	/**
	 * Fetches 5 music artists from the DB and resolves their sort labels
	 * against the result of the related SPARQL query.
	 * 
	 */
	@Test
	public void checkMusicArtistSortLabels()
	{
		ArrayList<String> classTables = new ArrayList<String>();
		ArrayList<String> classTableRows = new ArrayList<String>();

		classTables.add("artist");
		classTables.add("artist_name");

		classTableRows.add("sort_name");
		classTableRows.add("name");

		// add The Beatles as proof GUID
		TestResult testResult = Utils.getInstance()
				.checkSimplePropertyViaGUIDOnTheLeft(classTables,
						classTableRows, "mo:MusicArtist", "ov:sortLabel",
						"sortName", 1, 5, false, false, null,
						"b10bbbfc-cf9e-42e0-be17-e2c3e1d2600d",
						"ArtistSortLabelsCheck");

		assertTrue(testResult.getFailMsg(), testResult.isSucceeded());
	}

	/**
	 * Fetches 5 (+1) music artists from the DB and resolves their aliases
	 * against the result of the related SPARQL query.
	 * 
	 */
	@Test
	public void checkMusicArtistAliases()
	{
		ArrayList<String> classTables = new ArrayList<String>();
		ArrayList<String> classTableRows = new ArrayList<String>();

		classTables.add("artist");
		classTables.add("artist_alias");
		classTables.add("artist_name");

		classTableRows.add("name");
		classTableRows.add("artist");
		classTableRows.add("name");

		// add The Beatles as proof GUID
		TestResult testResult = Utils.getInstance()
				.checkSimplePropertyViaGUIDOnTheLeft(classTables,
						classTableRows, "mo:MusicArtist", "skos:altLabel",
						"alias", 2, 5, false, true, null,
						"b10bbbfc-cf9e-42e0-be17-e2c3e1d2600d",
						"ArtistsAliasesCheck");

		assertTrue(testResult.getFailMsg(), testResult.isSucceeded());
	}

	/**
	 * Fetches 5 music artists from the DB and resolves their genders against
	 * the result of the related SPARQL query.
	 * 
	 */
	@Test
	public void checkMusicArtistGender()
	{
		ArrayList<String> classTables = new ArrayList<String>();
		ArrayList<String> classTableRows = new ArrayList<String>();

		classTables.add("artist");
		classTables.add("gender");

		classTableRows.add("gender");
		classTableRows.add("name");

		// add Tori Amos as proof GUID
		TestResult testResult = Utils.getInstance()
				.checkSimplePropertyViaGUIDOnTheLeft(classTables,
						classTableRows, "mo:MusicArtist", "foaf:gender",
						"gender", 1, 5, false, false, null,
						"c0b2500e-0cef-4130-869d-732b23ed9df5",
						"ArtistGenderCheck");

		assertTrue(testResult.getFailMsg(), testResult.isSucceeded());
	}

	/**
	 * Fetches 5 (+1) music artists and their releases from the DB and resolves
	 * them via a SPARQL query.
	 * 
	 */
	@Test
	public void checkMusicArtistsReleasesRelations()
	{
		ArrayList<String> classTables = new ArrayList<String>();
		ArrayList<String> classTableRows = new ArrayList<String>();
		ArrayList<String> classNames = new ArrayList<String>();
		ArrayList<String> valueNames = new ArrayList<String>();

		classTables.add("artist");
		classTables.add("release");
		classTables.add("artist_credit_name");
		classTables.add("artist_credit");

		classTableRows.add("gid");
		classTableRows.add("gid");
		classTableRows.add("artist");
		classTableRows.add("artist_credit");
		classTableRows.add("artist_credit");

		classNames.add("mo:MusicArtist");
		classNames.add("mo:Release");

		valueNames.add("artistURI");
		valueNames.add("releaseURI");
		valueNames.add("releaseGUID");

		// add Tori Amos as proof GUID
		TestResult testResult = Utils.getInstance().checkURIPropertyViaGUIDs(
				classTables, classTableRows, classNames, "foaf:made",
				valueNames, null, null, 3, 5, null,
				"c0b2500e-0cef-4130-869d-732b23ed9df5",
				"ArtistsReleasesRelationsCheck");

		assertTrue(testResult.getFailMsg(), testResult.isSucceeded());
	}

	/**
	 * Fetches 5 (+1) music artists and their recordings from the DB and
	 * resolves them via a SPARQL query.
	 * 
	 */
	@Test
	public void checkMusicArtistsRecordingsRelations()
	{
		ArrayList<String> classTables = new ArrayList<String>();
		ArrayList<String> classTableRows = new ArrayList<String>();
		ArrayList<String> classNames = new ArrayList<String>();
		ArrayList<String> valueNames = new ArrayList<String>();

		classTables.add("artist");
		classTables.add("recording");
		classTables.add("artist_credit_name");
		classTables.add("artist_credit");

		classTableRows.add("gid");
		classTableRows.add("gid");
		classTableRows.add("artist");
		classTableRows.add("artist_credit");
		classTableRows.add("artist_credit");

		classNames.add("mo:MusicArtist");
		classNames.add("mo:Signal");

		valueNames.add("artistURI");
		valueNames.add("recordingURI");
		valueNames.add("recordingGUID");

		// add Tori Amos as proof GUID
		TestResult testResult = Utils.getInstance().checkURIPropertyViaGUIDs(
				classTables, classTableRows, classNames, "foaf:made",
				valueNames, null, null, 3, 5, null,
				"c0b2500e-0cef-4130-869d-732b23ed9df5",
				"ArtistsRecordingsReleationsCheck");

		assertTrue(testResult.getFailMsg(), testResult.isSucceeded());
	}

	/**
	 * Fetches 5 (+1) music artists and their works from the DB and resolves
	 * them via a SPARQL query.
	 * 
	 * ATTENTION: the 'artist_credit' row in the table 'work' is currently not
	 * used and might be removed in the future (that is why it won't deliver
	 * results at the moment)
	 * 
	 */
	@Test
	public void checkMusicArtistsWorksRelations()
	{
		ArrayList<String> classTables = new ArrayList<String>();
		ArrayList<String> classTableRows = new ArrayList<String>();
		ArrayList<String> classNames = new ArrayList<String>();
		ArrayList<String> valueNames = new ArrayList<String>();

		classTables.add("artist");
		classTables.add("work");
		classTables.add("artist_credit_name");
		classTables.add("artist_credit");

		classTableRows.add("gid");
		classTableRows.add("gid");
		classTableRows.add("artist");
		classTableRows.add("artist_credit");
		classTableRows.add("artist_credit");

		classNames.add("mo:MusicArtist");
		classNames.add("mo:MusicalWork");

		valueNames.add("artistURI");
		valueNames.add("workURI");
		valueNames.add("workGUID");

		// add Tori Amos as proof GUID
		TestResult testResult = Utils.getInstance().checkURIPropertyViaGUIDs(
				classTables, classTableRows, classNames, "foaf:made",
				valueNames, null, null, 3, 5, null,
				"c0b2500e-0cef-4130-869d-732b23ed9df5",
				"ArtistsWorksRelationsCheck");

		assertTrue(testResult.getFailMsg(), testResult.isSucceeded());
	}

	/**
	 * Fetches 5 (+1) music artists and their release groups from the DB and
	 * resolves them via a SPARQL query.
	 * 
	 */
	@Test
	public void checkMusicArtistsReleaseGroupsRelations()
	{
		ArrayList<String> classTables = new ArrayList<String>();
		ArrayList<String> classTableRows = new ArrayList<String>();
		ArrayList<String> classNames = new ArrayList<String>();
		ArrayList<String> valueNames = new ArrayList<String>();

		classTables.add("artist");
		classTables.add("release_group");
		classTables.add("artist_credit_name");
		classTables.add("artist_credit");

		classTableRows.add("gid");
		classTableRows.add("gid");
		classTableRows.add("artist");
		classTableRows.add("artist_credit");
		classTableRows.add("artist_credit");

		classNames.add("mo:MusicArtist");
		classNames.add("mo:SignalGroup");

		valueNames.add("artistURI");
		valueNames.add("releaseGroupURI");
		valueNames.add("releaseGroupGUID");

		// add Tori Amos as proof GUID
		TestResult testResult = Utils.getInstance().checkURIPropertyViaGUIDs(
				classTables, classTableRows, classNames, "foaf:made",
				valueNames, null, null, 3, 5, null,
				"c0b2500e-0cef-4130-869d-732b23ed9df5",
				"ArtistsReleaseGroupsRelationsCheck");

		assertTrue(testResult.getFailMsg(), testResult.isSucceeded());
	}

	/**
	 * Fetches 5 (+1) music artists and their tracks from the DB and resolves
	 * them via a SPARQL query.
	 * 
	 */
	@Test
	public void checkMusicArtistsTracksRelations()
	{
		ArrayList<String> classTables = new ArrayList<String>();
		ArrayList<String> classTableRows = new ArrayList<String>();
		ArrayList<String> classNames = new ArrayList<String>();
		ArrayList<String> valueNames = new ArrayList<String>();

		classTables.add("artist");
		classTables.add("track");
		classTables.add("artist_credit_name");
		classTables.add("artist_credit");

		classTableRows.add("gid");
		classTableRows.add("id");
		classTableRows.add("artist");
		classTableRows.add("artist_credit");
		classTableRows.add("artist_credit");

		classNames.add("mo:MusicArtist");
		classNames.add("mo:Track");

		valueNames.add("artistURI");
		valueNames.add("trackURI");
		valueNames.add("trackURI");

		// add Tori Amos as proof GUID
		TestResult testResult = Utils.getInstance()
				.checkURIPropertyViaGUIDOnTheLeftAndIDOnTheRight(classTables,
						classTableRows, classNames, "foaf:made", valueNames,
						"#_", 3, 1, "c0b2500e-0cef-4130-869d-732b23ed9df5",
						"ArtistsTracksRelationsCheck");

		assertTrue(testResult.getFailMsg(), testResult.isSucceeded());
	}

	/**
	 * Fetches 5 music artists from the DB and resolves their countries against
	 * the result of the related SPARQL query.
	 * 
	 */
	@Test
	public void checkMusicArtistCountry()
	{
		ArrayList<String> classTables = new ArrayList<String>();
		ArrayList<String> classTableRows = new ArrayList<String>();

		classTables.add("artist");
		classTables.add("country");

		classTableRows.add("country");
		classTableRows.add("iso_code");

		// add Tori Amos as proof GUID
		TestResult testResult = Utils.getInstance()
				.checkSimplePropertyViaGUIDOnTheLeft(classTables,
						classTableRows, "mo:MusicArtist", "foaf:based_near",
						"country", 1, 5, true, false, null,
						"c0b2500e-0cef-4130-869d-732b23ed9df5",
						"ArtistsCountryCheck");

		assertTrue(testResult.getFailMsg(), testResult.isSucceeded());
	}

	/**
	 * Fetches 5 (+1) music artists and their Wikipedia links from the DB and
	 * resolves them via a SPARQL query to DBPedia resource URIs.
	 * 
	 */
	@Test
	public void checkMusicArtistsDBPedialinksRelations()
	{
		ArrayList<String> classTables = new ArrayList<String>();
		ArrayList<String> classTableRows = new ArrayList<String>();
		ArrayList<String> classNames = new ArrayList<String>();
		ArrayList<String> valueNames = new ArrayList<String>();

		classTables.add("artist");
		classTables.add("url");
		classTables.add("l_artist_url");
		classTables.add("link");
		classTables.add("link_type");

		classTableRows.add("gid");
		classTableRows.add("url");
		classTableRows.add("entity0");
		classTableRows.add("link");
		classTableRows.add("link_type");
		classTableRows.add("entity1");

		classNames.add("mo:MusicArtist");

		valueNames.add("artistURI");
		valueNames.add("dbpediaURI");
		valueNames.add("dbpediaURI");

		// add The Beatles as proof GUID
		TestResult testResult = Utils
				.getInstance()
				.checkURIPropertyViaGUIDOnTheLeftAndURIOnTheRight(
						classTables,
						classTableRows,
						classNames,
						"owl:sameAs",
						valueNames,
						4,
						5,
						new URICondition("link_type", "gid",
								"'29651736-fa6d-48e4-aadc-a557c6add1cb'", true,
								"is:info_service", "isi:dbpedia",
								"linkedbrainz.d2rs.translator.DBPediaTranslator"),
						"b10bbbfc-cf9e-42e0-be17-e2c3e1d2600d",
						"ArtistsDBPedialinksRelationsCheck");

		assertTrue(testResult.getFailMsg(), testResult.isSucceeded());
	}

	/**
	 * Fetches 5 (+1) music artists and their Wikipedia links from the DB and
	 * resolves them via a SPARQL query to cleaned up Wikipedia URLs.
	 * 
	 */
	@Test
	public void checkMusicArtistsWikipedialinksRelations()
	{
		ArrayList<String> classTables = new ArrayList<String>();
		ArrayList<String> classTableRows = new ArrayList<String>();
		ArrayList<String> classNames = new ArrayList<String>();
		ArrayList<String> valueNames = new ArrayList<String>();

		classTables.add("artist");
		classTables.add("url");
		classTables.add("l_artist_url");
		classTables.add("link");
		classTables.add("link_type");

		classTableRows.add("gid");
		classTableRows.add("url");
		classTableRows.add("entity0");
		classTableRows.add("link");
		classTableRows.add("link_type");
		classTableRows.add("entity1");

		classNames.add("mo:MusicArtist");

		valueNames.add("artistURI");
		valueNames.add("wikiURI");
		valueNames.add("wikiURI");

		// add The Beatles as proof GUID
		TestResult testResult = Utils
				.getInstance()
				.checkURIPropertyViaGUIDOnTheLeftAndURIOnTheRight(
						classTables,
						classTableRows,
						classNames,
						"rdfs:seeAlso",
						valueNames,
						4,
						5,
						new URICondition("link_type", "gid",
								"'29651736-fa6d-48e4-aadc-a557c6add1cb'", true,
								"is:info_service", "isi:wikipedia",
								"linkedbrainz.d2rs.translator.WikipediaTranslator"),
						"b10bbbfc-cf9e-42e0-be17-e2c3e1d2600d",
						"ArtistsWikipedialinksRelationsCheck");

		assertTrue(testResult.getFailMsg(), testResult.isSucceeded());
	}

	/**
	 * Fetches 5 (+1) music artists and their Discogs links from the DB and
	 * resolves them via a SPARQL query to DataIncubator Discogs resource URIs.
	 * 
	 */
	@Test
	public void checkMusicArtistsDataIncubatorDiscogslinksRelations()
	{
		ArrayList<String> classTables = new ArrayList<String>();
		ArrayList<String> classTableRows = new ArrayList<String>();
		ArrayList<String> classNames = new ArrayList<String>();
		ArrayList<String> valueNames = new ArrayList<String>();

		classTables.add("artist");
		classTables.add("url");
		classTables.add("l_artist_url");
		classTables.add("link");
		classTables.add("link_type");

		classTableRows.add("gid");
		classTableRows.add("url");
		classTableRows.add("entity0");
		classTableRows.add("link");
		classTableRows.add("link_type");
		classTableRows.add("entity1");

		classNames.add("mo:MusicArtist");

		valueNames.add("artistURI");
		valueNames.add("dataIncubatorDiscogsURI");
		valueNames.add("dataIncubatorDiscogsURI");

		// add The Beatles as proof GUID
		TestResult testResult = Utils
				.getInstance()
				.checkURIPropertyViaGUIDOnTheLeftAndURIOnTheRight(
						classTables,
						classTableRows,
						classNames,
						"owl:sameAs",
						valueNames,
						4,
						5,
						new URICondition("link_type", "gid",
								"'04a5b104-a4c2-4bac-99a1-7b837c37d9e4'", true,
								"is:info_service", "isi:dataincubatordiscogs",
								"linkedbrainz.d2rs.translator.DataIncubatorDiscogsTranslator"),
						"b10bbbfc-cf9e-42e0-be17-e2c3e1d2600d",
						"ArtistsDataIncubatorDiscogslinksReleationsCheck");

		assertTrue(testResult.getFailMsg(), testResult.isSucceeded());
	}

	/**
	 * Fetches 5 (+1) music artists and their Discogs links from the DB and
	 * resolves them via a SPARQL query to cleaned up Discogs page URLs.
	 * 
	 */
	@Test
	public void checkMusicArtistsDiscogslinksRelations()
	{
		ArrayList<String> classTables = new ArrayList<String>();
		ArrayList<String> classTableRows = new ArrayList<String>();
		ArrayList<String> classNames = new ArrayList<String>();
		ArrayList<String> valueNames = new ArrayList<String>();

		classTables.add("artist");
		classTables.add("url");
		classTables.add("l_artist_url");
		classTables.add("link");
		classTables.add("link_type");

		classTableRows.add("gid");
		classTableRows.add("url");
		classTableRows.add("entity0");
		classTableRows.add("link");
		classTableRows.add("link_type");
		classTableRows.add("entity1");

		classNames.add("mo:MusicArtist");

		valueNames.add("artistURI");
		valueNames.add("discogsURI");
		valueNames.add("discogsURI");

		// add The Beatles as proof GUID
		TestResult testResult = Utils
				.getInstance()
				.checkURIPropertyViaGUIDOnTheLeftAndURIOnTheRight(
						classTables,
						classTableRows,
						classNames,
						"rdfs:seeAlso",
						valueNames,
						4,
						5,
						new URICondition("link_type", "gid",
								"'04a5b104-a4c2-4bac-99a1-7b837c37d9e4'", true,
								"is:info_service", "isi:discogs",
								"linkedbrainz.d2rs.translator.DiscogsTranslator"),
						"b10bbbfc-cf9e-42e0-be17-e2c3e1d2600d",
						"ArtistsDiscogslinksReleationsCheck");

		assertTrue(testResult.getFailMsg(), testResult.isSucceeded());
	}

	/**
	 * Fetches 5 (+1) music artists and their BBC links from the DB and resolves
	 * them via a SPARQL query to BBC resource URIs.
	 * 
	 */
	@Test
	public void checkMusicArtistsBBClinksRelations()
	{
		ArrayList<String> classTables = new ArrayList<String>();
		ArrayList<String> classTableRows = new ArrayList<String>();
		ArrayList<String> classNames = new ArrayList<String>();
		ArrayList<String> valueNames = new ArrayList<String>();

		classTables.add("artist");
		classTables.add("url");
		classTables.add("l_artist_url");
		classTables.add("link");
		classTables.add("link_type");

		classTableRows.add("gid");
		classTableRows.add("url");
		classTableRows.add("entity0");
		classTableRows.add("link");
		classTableRows.add("link_type");
		classTableRows.add("entity1");

		classNames.add("mo:MusicArtist");

		valueNames.add("artistURI");
		valueNames.add("bbcURI");
		valueNames.add("bbcURI");

		// add The Beatles as proof GUID
		TestResult testResult = Utils.getInstance()
				.checkURIPropertyViaGUIDOnTheLeftAndURIOnTheRight(
						classTables,
						classTableRows,
						classNames,
						"owl:sameAs",
						valueNames,
						4,
						5,
						new URICondition("link_type", "gid",
								"'d028a975-000c-4525-9333-d3c8425e4b54'", true,
								"is:info_service", "isi:bbc",
								"linkedbrainz.d2rs.translator.BBCTranslator"),
						"b10bbbfc-cf9e-42e0-be17-e2c3e1d2600d",
						"ArtistsBBClinksRelationsCheck");

		assertTrue(testResult.getFailMsg(), testResult.isSucceeded());
	}

	/**
	 * Fetches 5 (+1) music artists and their homepages from the DB and resolves
	 * them via a SPARQL query.
	 * 
	 */
	@Test
	public void checkMusicArtistsHomepagesRelations()
	{
		ArrayList<String> classTables = new ArrayList<String>();
		ArrayList<String> classTableRows = new ArrayList<String>();
		ArrayList<String> classNames = new ArrayList<String>();
		ArrayList<String> valueNames = new ArrayList<String>();

		classTables.add("artist");
		classTables.add("url");
		classTables.add("l_artist_url");
		classTables.add("link");
		classTables.add("link_type");

		classTableRows.add("gid");
		classTableRows.add("url");
		classTableRows.add("entity0");
		classTableRows.add("link");
		classTableRows.add("link_type");
		classTableRows.add("entity1");

		classNames.add("mo:MusicArtist");

		valueNames.add("artistURI");
		valueNames.add("homepageURI");
		valueNames.add("homepageURI");

		// add The Beatles as proof GUID
		TestResult testResult = Utils
				.getInstance()
				.checkURIPropertyViaGUIDOnTheLeftAndURIOnTheRight(
						classTables,
						classTableRows,
						classNames,
						"foaf:homepage",
						valueNames,
						4,
						5,
						new Condition("link_type", "gid",
								"'fe33d22f-c3b0-4d68-bd53-a856badf2b15'", true),
						"b10bbbfc-cf9e-42e0-be17-e2c3e1d2600d",
						"ArtistsHomepagesReleationsCheck");

		assertTrue(testResult.getFailMsg(), testResult.isSucceeded());
	}

	/**
	 * Fetches 5 (+1) music artists and their MySpace links from the DB and
	 * resolves them via a SPARQL query to DBTune MySpace URIs.
	 * 
	 */
	@Test
	public void checkMusicArtistsDBTuneMySpacelinksRelations()
	{
		ArrayList<String> classTables = new ArrayList<String>();
		ArrayList<String> classTableRows = new ArrayList<String>();
		ArrayList<String> classNames = new ArrayList<String>();
		ArrayList<String> valueNames = new ArrayList<String>();

		classTables.add("artist");
		classTables.add("url");
		classTables.add("l_artist_url");
		classTables.add("link");
		classTables.add("link_type");

		classTableRows.add("gid");
		classTableRows.add("url");
		classTableRows.add("entity0");
		classTableRows.add("link");
		classTableRows.add("link_type");
		classTableRows.add("entity1");

		classNames.add("mo:MusicArtist");

		valueNames.add("artistURI");
		valueNames.add("dbtuneMyspaceURI");
		valueNames.add("dbtuneMyspaceURI");

		// add The Beatles as proof GUID
		TestResult testResult = Utils
				.getInstance()
				.checkURIPropertyViaGUIDOnTheLeftAndURIOnTheRight(
						classTables,
						classTableRows,
						classNames,
						"owl:sameAs",
						valueNames,
						4,
						5,
						new URICondition("link_type", "gid",
								"'bac47923-ecde-4b59-822e-d08f0cd10156'", true,
								"is:info_service", "isi:dbtunemyspace",
								"linkedbrainz.d2rs.translator.DBTuneMySpaceTranslator"),
						"b10bbbfc-cf9e-42e0-be17-e2c3e1d2600d",
						"ArtistsDBTuneMyspacelinksReleationsCheck");

		assertTrue(testResult.getFailMsg(), testResult.isSucceeded());
	}

	/**
	 * Fetches 5 (+1) music artists and their MySpace links from the DB and
	 * resolves them via a SPARQL query to cleaned up MySpace page URLs.
	 * 
	 */
	@Test
	public void checkMusicArtistsMySpacelinksRelations()
	{
		ArrayList<String> classTables = new ArrayList<String>();
		ArrayList<String> classTableRows = new ArrayList<String>();
		ArrayList<String> classNames = new ArrayList<String>();
		ArrayList<String> valueNames = new ArrayList<String>();

		classTables.add("artist");
		classTables.add("url");
		classTables.add("l_artist_url");
		classTables.add("link");
		classTables.add("link_type");

		classTableRows.add("gid");
		classTableRows.add("url");
		classTableRows.add("entity0");
		classTableRows.add("link");
		classTableRows.add("link_type");
		classTableRows.add("entity1");

		classNames.add("mo:MusicArtist");

		valueNames.add("artistURI");
		valueNames.add("myspaceURI");
		valueNames.add("myspaceURI");

		// add The Beatles as proof GUID
		TestResult testResult = Utils
				.getInstance()
				.checkURIPropertyViaGUIDOnTheLeftAndURIOnTheRight(
						classTables,
						classTableRows,
						classNames,
						"rdfs:seeAlso",
						valueNames,
						4,
						5,
						new URICondition("link_type", "gid",
								"'bac47923-ecde-4b59-822e-d08f0cd10156'", true,
								"is:info_service", "isi:myspace",
								"linkedbrainz.d2rs.translator.MySpaceTranslator"),
						"b10bbbfc-cf9e-42e0-be17-e2c3e1d2600d",
						"ArtistsMyspacelinksReleationsCheck");

		assertTrue(testResult.getFailMsg(), testResult.isSucceeded());
	}

	/**
	 * Fetches 5 (+1) music artists and their YouTube channels from the DB and
	 * resolves them via a SPARQL query.
	 * 
	 */
	@Test
	public void checkMusicArtistsYouTubeChannelsRelations()
	{
		ArrayList<String> classTables = new ArrayList<String>();
		ArrayList<String> classTableRows = new ArrayList<String>();
		ArrayList<String> classNames = new ArrayList<String>();
		ArrayList<String> valueNames = new ArrayList<String>();

		classTables.add("artist");
		classTables.add("url");
		classTables.add("l_artist_url");
		classTables.add("link");
		classTables.add("link_type");

		classTableRows.add("gid");
		classTableRows.add("url");
		classTableRows.add("entity0");
		classTableRows.add("link");
		classTableRows.add("link_type");
		classTableRows.add("entity1");

		classNames.add("mo:MusicArtist");

		valueNames.add("artistURI");
		valueNames.add("youTubeChannelURI");
		valueNames.add("youTubeChannelURI");

		// add The Beatles as proof GUID
		TestResult testResult = Utils
				.getInstance()
				.checkURIPropertyViaGUIDOnTheLeftAndURIOnTheRight(
						classTables,
						classTableRows,
						classNames,
						"rdfs:seeAlso",
						valueNames,
						4,
						5,
						new URICondition("link_type", "gid",
								"'6a540e5b-58c6-4192-b6ba-dbc71ec8fcf0'", true,
								"is:info_service", "isi:youtube",
								"linkedbrainz.d2rs.translator.YouTubeTranslator"),
						"b10bbbfc-cf9e-42e0-be17-e2c3e1d2600d",
						"ArtistsYouTubeChannelsReleationsCheck");

		assertTrue(testResult.getFailMsg(), testResult.isSucceeded());
	}

	/**
	 * Fetches 5 (+1) artists and their IBDb links from the DB and resolves them
	 * via a SPARQL query to cleaned up IBDb page URLs.
	 * 
	 */
	@Test
	public void checkArtistsIBDblinksRelations()
	{
		ArrayList<String> classTables = new ArrayList<String>();
		ArrayList<String> classTableRows = new ArrayList<String>();
		ArrayList<String> classNames = new ArrayList<String>();
		ArrayList<String> valueNames = new ArrayList<String>();

		classTables.add("artist");
		classTables.add("url");
		classTables.add("l_artist_url");
		classTables.add("link");
		classTables.add("link_type");

		classTableRows.add("gid");
		classTableRows.add("url");
		classTableRows.add("entity0");
		classTableRows.add("link");
		classTableRows.add("link_type");
		classTableRows.add("entity1");

		classNames.add("mo:MusicArtist");

		valueNames.add("artistURI");
		valueNames.add("ibdbURI");
		valueNames.add("ibdbURI");

		// add Stephen Sondheim as proof
		// GUID
		TestResult testResult = Utils.getInstance()
				.checkURIPropertyViaGUIDOnTheLeftAndURIOnTheRight(
						classTables,
						classTableRows,
						classNames,
						"rdfs:seeAlso",
						valueNames,
						4,
						5,
						new URICondition("link_type", "gid",
								"'5728c659-56b2-4e23-97d1-80e1f229c7d3'", true,
								"is:info_service", "isi:ibdb",
								"linkedbrainz.d2rs.translator.IBDBTranslator"),
						"bcd6af9f-afa8-43fd-b1be-acbbbb2f7dc7",
						"ArtistsIBDblinksReleationsCheck");

		assertTrue(testResult.getFailMsg(), testResult.isSucceeded());
	}

	/**
	 * Fetches 5 (+1) artists and their IMDb links from the DB and resolves them
	 * via a SPARQL query to cleaned up IMDb page URLs.
	 * 
	 */
	@Test
	public void checkArtistsIMDblinksRelations()
	{
		ArrayList<String> classTables = new ArrayList<String>();
		ArrayList<String> classTableRows = new ArrayList<String>();
		ArrayList<String> classNames = new ArrayList<String>();
		ArrayList<String> valueNames = new ArrayList<String>();

		classTables.add("artist");
		classTables.add("url");
		classTables.add("l_artist_url");
		classTables.add("link");
		classTables.add("link_type");

		classTableRows.add("gid");
		classTableRows.add("url");
		classTableRows.add("entity0");
		classTableRows.add("link");
		classTableRows.add("link_type");
		classTableRows.add("entity1");

		classNames.add("mo:MusicArtist");

		valueNames.add("artistURI");
		valueNames.add("imdbURI");
		valueNames.add("imdbURI");

		// add Stevie Wonder proof
		// GUID
		TestResult testResult = Utils.getInstance()
				.checkURIPropertyViaGUIDOnTheLeftAndURIOnTheRight(
						classTables,
						classTableRows,
						classNames,
						"rdfs:seeAlso",
						valueNames,
						4,
						5,
						new URICondition("link_type", "gid",
								"'94c8b0cc-4477-4106-932c-da60e63de61c'", true,
								"is:info_service", "isi:imdb",
								"linkedbrainz.d2rs.translator.IMDBTranslator"),
						"1ee18fb3-18a6-4c7f-8ba0-bc41cdd0462e",
						"ArtistsIMDblinksReleationsCheck");

		assertTrue(testResult.getFailMsg(), testResult.isSucceeded());
	}

	/**
	 * Fetches 5 (+1) artists and their IOBDb links from the DB and resolves
	 * them via a SPARQL query to cleaned up IOBDb page URLs.
	 * 
	 */
	@Test
	public void checkArtistsIOBDblinksRelations()
	{
		ArrayList<String> classTables = new ArrayList<String>();
		ArrayList<String> classTableRows = new ArrayList<String>();
		ArrayList<String> classNames = new ArrayList<String>();
		ArrayList<String> valueNames = new ArrayList<String>();

		classTables.add("artist");
		classTables.add("url");
		classTables.add("l_artist_url");
		classTables.add("link");
		classTables.add("link_type");

		classTableRows.add("gid");
		classTableRows.add("url");
		classTableRows.add("entity0");
		classTableRows.add("link");
		classTableRows.add("link_type");
		classTableRows.add("entity1");

		classNames.add("mo:MusicArtist");

		valueNames.add("artistURI");
		valueNames.add("iobdbURI");
		valueNames.add("iobdbURI");

		// add Stephen Sondheim proof
		// GUID
		TestResult testResult = Utils
				.getInstance()
				.checkURIPropertyViaGUIDOnTheLeftAndURIOnTheRight(
						classTables,
						classTableRows,
						classNames,
						"rdfs:seeAlso",
						valueNames,
						4,
						5,
						new URICondition("link_type", "gid",
								"'689043e3-2b9e-47ba-ad86-2742589e0743'", true,
								"is:info_service", "isi:iobdb",
								"linkedbrainz.d2rs.translator.IOBDBTranslator"),
						"bcd6af9f-afa8-43fd-b1be-acbbbb2f7dc7",
						"ArtistsIOBDblinksReleationsCheck");

		assertTrue(testResult.getFailMsg(), testResult.isSucceeded());
	}

	/**
	 * Fetches 5 (+1) artists and their MusicMoz links from the DB and resolves
	 * them via a SPARQL query to cleaned up MusicMoz page URLs.
	 * 
	 */
	@Test
	public void checkArtistsMusicMozlinksRelations()
	{
		ArrayList<String> classTables = new ArrayList<String>();
		ArrayList<String> classTableRows = new ArrayList<String>();
		ArrayList<String> classNames = new ArrayList<String>();
		ArrayList<String> valueNames = new ArrayList<String>();

		classTables.add("artist");
		classTables.add("url");
		classTables.add("l_artist_url");
		classTables.add("link");
		classTables.add("link_type");

		classTableRows.add("gid");
		classTableRows.add("url");
		classTableRows.add("entity0");
		classTableRows.add("link");
		classTableRows.add("link_type");
		classTableRows.add("entity1");

		classNames.add("mo:MusicArtist");

		valueNames.add("artistURI");
		valueNames.add("musicMozURI");
		valueNames.add("musicMozURI");

		// add Massive Attack proof
		// GUID
		TestResult testResult = Utils
				.getInstance()
				.checkURIPropertyViaGUIDOnTheLeftAndURIOnTheRight(
						classTables,
						classTableRows,
						classNames,
						"rdfs:seeAlso",
						valueNames,
						4,
						5,
						new URICondition("link_type", "gid",
								"'ded9a80a-e6de-4831-880c-c78b9981b54b'", true,
								"is:info_service", "isi:musicmoz",
								"linkedbrainz.d2rs.translator.MusicMozTranslator"),
						"10adbe5e-a2c0-4bf3-8249-2b4cbf6e6ca8",
						"ArtistsMusicMozlinksReleationsCheck");

		assertTrue(testResult.getFailMsg(), testResult.isSucceeded());
	}

	/**
	 * Fetches 5 (+1) artists and their PureVolume links from the DB and
	 * resolves them via a SPARQL query to cleaned up PureVolume page URLs.
	 * 
	 */
	@Test
	public void checkArtistsPureVolumelinksRelations()
	{
		ArrayList<String> classTables = new ArrayList<String>();
		ArrayList<String> classTableRows = new ArrayList<String>();
		ArrayList<String> classNames = new ArrayList<String>();
		ArrayList<String> valueNames = new ArrayList<String>();

		classTables.add("artist");
		classTables.add("url");
		classTables.add("l_artist_url");
		classTables.add("link");
		classTables.add("link_type");

		classTableRows.add("gid");
		classTableRows.add("url");
		classTableRows.add("entity0");
		classTableRows.add("link");
		classTableRows.add("link_type");
		classTableRows.add("entity1");

		classNames.add("mo:MusicArtist");

		valueNames.add("artistURI");
		valueNames.add("pureVolumeURI");
		valueNames.add("pureVolumeURI");

		// add De La Soul proof
		// GUID
		TestResult testResult = Utils
				.getInstance()
				.checkURIPropertyViaGUIDOnTheLeftAndURIOnTheRight(
						classTables,
						classTableRows,
						classNames,
						"rdfs:seeAlso",
						valueNames,
						4,
						5,
						new URICondition("link_type", "gid",
								"'b6f02157-a9d3-4f24-9057-0675b2dbc581'", true,
								"is:info_service", "isi:purevolume",
								"linkedbrainz.d2rs.translator.PureVolumeTranslator"),
						"a8ebde98-7e91-46c7-992c-90039ba42017",
						"ArtistsPureVolumelinksReleationsCheck");

		assertTrue(testResult.getFailMsg(), testResult.isSucceeded());
	}

	/**
	 * Fetches 5 (+1) artists and their VGMdb links from the DB and resolves
	 * them via a SPARQL query to cleaned up VGMdb page URLs.
	 * 
	 */
	@Test
	public void checkArtistsVGMDBlinksRelations()
	{
		ArrayList<String> classTables = new ArrayList<String>();
		ArrayList<String> classTableRows = new ArrayList<String>();
		ArrayList<String> classNames = new ArrayList<String>();
		ArrayList<String> valueNames = new ArrayList<String>();

		classTables.add("artist");
		classTables.add("url");
		classTables.add("l_artist_url");
		classTables.add("link");
		classTables.add("link_type");

		classTableRows.add("gid");
		classTableRows.add("url");
		classTableRows.add("entity0");
		classTableRows.add("link");
		classTableRows.add("link_type");
		classTableRows.add("entity1");

		classNames.add("mo:MusicArtist");

		valueNames.add("artistURI");
		valueNames.add("vgmdbURI");
		valueNames.add("vgmdbURI");

		// add Davy Spillane proof
		// GUID
		TestResult testResult = Utils
				.getInstance()
				.checkURIPropertyViaGUIDOnTheLeftAndURIOnTheRight(
						classTables,
						classTableRows,
						classNames,
						"rdfs:seeAlso",
						valueNames,
						4,
						5,
						new URICondition("link_type", "gid",
								"'0af15ab3-c615-46d6-b95b-a5fcd2a92ed9'", true,
								"is:info_service", "isi:vgmdb",
								"linkedbrainz.d2rs.translator.VGMDBTranslator"),
						"135b658e-4409-404f-a64e-f1f8e5c2866d",
						"ArtistsVGMDBlinksReleationsCheck");

		assertTrue(testResult.getFailMsg(), testResult.isSucceeded());
	}

	public static junit.framework.Test suite()
	{
		return new JUnit4TestAdapter(ArtistTest.class);
	}
}
