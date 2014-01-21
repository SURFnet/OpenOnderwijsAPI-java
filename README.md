OpenOnderwijsAPI-java
========================

### Importing the library into your project
+ If you want to be sure that you are using the latest library, build the project yourself as described at the bottom.
+ There's also a library jar available in the Library folder named `oda.jar`.
+ Import the library jar (oda.jar, or the one that you have built) into your project.

### Using the client
There's an Android demo included, there you can see, how you should use the library.
+ For the communication with the API, create a new OnderWijsDataAPI object:
  `OnderwijsDataAPI apiClient = new OnderwijsDataAPI("http://your.api.url");`
+ Now you can get the different sub-APIs for getting different data. Getting the sub-API for persons:
  `apiClient.getPersonsClient()`
+ Use the `get(...)` and the `getList(...)` methods to fetch the data from the sub-API. Both have a callback, which can return with a success or failure method.
+ With `get(id, params, handler)` you fetch data of a single object. The **id** is the unique identifier of the object. **Remember: this is not always a number!** The **params** is a Params type of object. You can create this the following way:
  ```java

  Params params = new Params();
  params.setPage(1); //adds the &page=1 parameter to the query
  params.put("sort","name"); // you can also add custom parameters
  ```
+ `getList(params, handler)` works the same way as get, only it returns a list of objects.

### Questions, bugs, enhancements
For questions about usage, or if you found a bug, you can use the Issues section of the repository. Also, pull requests for enhancements are welcome :)

### Using the repository as a submodule in your project
+ Copy the URL of this repository.
+ In your command window, go to the directory, where you want to place the downloaded sources.
+ Enter `git submodule add https://github.com/repository_url`, where the last part is the URL you copied.
+ Git now downloads the repository to the directory, where you currently are.
+ You are now using this repository as a submodule :)

### If you want to change the code

#### Building the project
1. Open the project using **eclipse**.
2. Install the **FatJar plugin** using this URL from the update manager: `http://kurucz-grafika.de/fatjar`
3. Right-click on your project, and select `Build Fat Jar`
4. Using the default settings build your .jar. Always build your project before creating the fat jar, or the changes will not be available in the fat jar.

### License
This Java API client uses the following libraries:

[**Retrofit** by Square, Inc.](http://square.github.io/retrofit/)

[**google-gson**](https://code.google.com/p/google-gson/)
