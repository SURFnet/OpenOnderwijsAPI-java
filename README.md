OpenOnderwijsAPI-java
========================

### Building the project
1. Open the project using **eclipse**.
2. Install the **FatJar plugin** using this URL from the update manager: `http://kurucz-grafika.de/fatjar`
3. Right-click on your project, and select `Build Fat Jar`
4. Using the default settings build your .jar. Always build your project before creating the fat jar, or the changes will not be available in the fat jar.

### Importing the library into your project
+ If you want to be sure that you are using the latest library, build the project yourself as described above.
+ There's also a library jar available in the Library folder named `oda.jar`.
+ Import the library jar into your project.

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

### License
This Java API client uses the following libraries:

[**Retrofit** by Square, Inc.](http://square.github.io/retrofit/)
```
Copyright 2013 Square, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
[**google-gson**](https://code.google.com/p/google-gson/)
```
Copyright (c) 2008-2009 Google Inc. 

Licensed under the Apache License, Version 2.0 (the "License"); 
you may not use this file except in compliance with the License. 
You may obtain a copy of the License at 

  http://www.apache.org/licenses/LICENSE-2.0 

Unless required by applicable law or agreed to in writing, software 
distributed under the License is distributed on an "AS IS" BASIS, 
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
See the License for the specific language governing permissions and 
limitations under the License.
```
