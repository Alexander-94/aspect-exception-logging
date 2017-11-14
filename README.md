# aspect-exception-logging
Using of aspect-oriented programming for exception logging

>Project provides logging WatchController.getWatchByTitle(), WatchController.addNewWatch() methods.

>**Structure:**

defined @Pointcuts:
  -   controllerMethodsWithoutStatistics() - for getWatchByTitle() exception logging
  -   controllerMethodsWatchController()   - for addNewWatch()     exception logging
  
defined @Around annotations for:
  -   WatchController.addNewWatch()     -  @Around("exactControllerAddNewWatchMethod()")
  -   WatchController.getWatchByTitle() -  @Around("controllerMethodsWithoutStatistics()")
  
using JSON for exception return in methods:

      JSONObject response = new JSONObject();
      response.put("ASP watchTitle errorMessage", e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
      .header("Error", "Server error occurred during ... request").body(response);
