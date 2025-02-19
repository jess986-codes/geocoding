# geocoding
This is a Spring Boot REST API which returns returns the corresponding Australia state when given coordinates.

## How It Works
It takes in the boundary coordinates of each Australian state, which is located in the following folder:
```
src/main/resources/australian_states.geojson
```
Each state is given a polygon representation, so that when a coordinate set is given we can determine whether that point sits within any of the state polygons.

If the coordinates do not sit within any of the state, then API returns 'State not found'.

## Retrieving State Coordinates
These coordinates were taken from [rowanhogan's Australia State repo](https://github.com/rowanhogan/australian-states).

Alternatively, the coordinates can be taken from https://data.gov.au/ by searching for each state's boundary data and downloding the GeoJSON file:
- [VIC State Boundary](https://data.gov.au/dataset/ds-dga-b90c2a19-d978-4e14-bb15-1114b46464fb/details?q=victoria%20state%20boundary)
- [WA State Boundary](https://data.gov.au/dataset/ds-dga-5c00d495-21ba-452d-ae46-1ad0ca05e41f/details?q=wa%20boundary)
- [ACT State Boundary](https://data.gov.au/dataset/ds-dga-83468f0c-313d-4354-9592-289554eb2dc9/details?q=ACT%20state%20boundary)
- [NSW State Boundary](https://data.gov.au/dataset/ds-dga-a1b278b1-59ef-4dea-8468-50eb09967f18/details?q=NSW%20state%20boundary)
- [QLD State Boundary](https://data.gov.au/dataset/ds-dga-2dbbec1a-99a2-4ee5-8806-53bc41d038a7/details?q=QLD%20state%20boundary)
- [TAS State Boundary](https://data.gov.au/dataset/ds-dga-cf2ebc53-1633-4c5c-b892-bfc3945d913b/details?q=TAS%20state%20boundary)
- [SA State Boundary](https://data.gov.au/dataset/ds-dga-8f996b8c-d939-4757-a231-3fec8cb8e929/details?q=SA%20state%20boundary)
- [NT State Boundary](https://data.gov.au/dataset/ds-dga-5162e11c-3259-4894-8b9e-f44540b6cb11/details?q=NT%20state%20boundary)

You will need to merge the files together into one GeoJSON file for this API.
> TO DO: Make a script that automatically does this by pulling the above files from data.gov.au site. Therefore, when there are updates to the coorindates published by the data.gov.au site, this file can be easily constructed again.


## How to run
In the root project directory, run the following command:
```
mvn spring-boot:run
```

In Postman or your browser, send a GET request to port 8080 (or whichever available port the application is running from as indicated in the terminal logs). The latitude and longitude are passed in as 'lat' and 'lon' query parameters:
```
http://localhost:8080/get-state?lat=-19.12713045882208&lon=122.95705585060765
```
The above returns WA as the state.


