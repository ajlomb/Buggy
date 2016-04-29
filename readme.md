<H1>
Buggy, Insect Identifier
</H1>



Main activity upon launch:
![](/Users/alexanderlombardo/Desktop/Project2Buggy/MainActivity.jpg)

Main page with search queried:
![](/Users/alexanderlombardo/Desktop/Project2Buggy/MainSearch.jpg)

<div>Details page from clicked search result:
![](/Users/alexanderlombardo/Desktop/Project2Buggy/DetailsActivity.jpg)


![](/Users/alexanderlombardo/Desktop/Project2Buggy/DetailsFavorited.jpg)

Data structure/ERD:
![](/Users/alexanderlombardo/Desktop/Project2Buggy/ERDandTable.jpg)

	MainActivity UserStories	
User	Opens app	Home page, Displays insect of the day(IotD) at bottom, above search return field

User	Clicks search icon on top of screen,	Allow user to add text to search field

User	Performs search with text in field,	Displays search results above IotD frame in list view

User	Clicks an insect in the returned search list,	Sends user to details activity for selected insect

User	Clicks on Insect of the Day,	Sends user to details activity for selected insect
		
	DetailsActivity UserStories	
User	Loads Details activity,	Displays an image and information about selected Insect

User	Clicks floating action button “Favorites”,	Corresponding insect is saved to favorites list
		
	TestCases	
User	See home screen,	validate list view/Insect of the Day(IotD)

User	Searches for “wings”, “wing”, case-insensitive,	insects with wings return in the search bar

User	See home screen,	click search icon button(return true?) to test button

User	clicks on a returned search item; “bee”,	Sends user to details activity for bee

User	Loads Details activity,	validate image/textview(?)

User	Searches “8” or “eight” legs,	returns spider results

User	Searches “6” or “six” legs,	returns insect results

