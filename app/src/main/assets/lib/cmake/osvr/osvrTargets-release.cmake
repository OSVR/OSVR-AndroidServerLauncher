#----------------------------------------------------------------
# Generated CMake target import file for configuration "Release".
#----------------------------------------------------------------

# Commands may need to know the format version.
set(CMAKE_IMPORT_FILE_VERSION 1)

# Import target "osvr::osvrUtil" for configuration "Release"
set_property(TARGET osvr::osvrUtil APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(osvr::osvrUtil PROPERTIES
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libosvrUtil.so"
  IMPORTED_SONAME_RELEASE "libosvrUtil.so"
  )

list(APPEND _IMPORT_CHECK_TARGETS osvr::osvrUtil )
list(APPEND _IMPORT_CHECK_FILES_FOR_osvr::osvrUtil "${_IMPORT_PREFIX}/lib/libosvrUtil.so" )

# Import target "osvr::osvrCommon" for configuration "Release"
set_property(TARGET osvr::osvrCommon APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(osvr::osvrCommon PROPERTIES
  IMPORTED_LINK_DEPENDENT_LIBRARIES_RELEASE "jsoncpp_lib"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libosvrCommon.so"
  IMPORTED_SONAME_RELEASE "libosvrCommon.so"
  )

list(APPEND _IMPORT_CHECK_TARGETS osvr::osvrCommon )
list(APPEND _IMPORT_CHECK_FILES_FOR_osvr::osvrCommon "${_IMPORT_PREFIX}/lib/libosvrCommon.so" )

# Import target "osvr::osvrPluginHost" for configuration "Release"
set_property(TARGET osvr::osvrPluginHost APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(osvr::osvrPluginHost PROPERTIES
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libosvrPluginHost.so"
  IMPORTED_SONAME_RELEASE "libosvrPluginHost.so"
  )

list(APPEND _IMPORT_CHECK_TARGETS osvr::osvrPluginHost )
list(APPEND _IMPORT_CHECK_FILES_FOR_osvr::osvrPluginHost "${_IMPORT_PREFIX}/lib/libosvrPluginHost.so" )

# Import target "osvr::osvrConnection" for configuration "Release"
set_property(TARGET osvr::osvrConnection APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(osvr::osvrConnection PROPERTIES
  IMPORTED_LINK_DEPENDENT_LIBRARIES_RELEASE "osvr::osvrCommon"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libosvrConnection.so"
  IMPORTED_SONAME_RELEASE "libosvrConnection.so"
  )

list(APPEND _IMPORT_CHECK_TARGETS osvr::osvrConnection )
list(APPEND _IMPORT_CHECK_FILES_FOR_osvr::osvrConnection "${_IMPORT_PREFIX}/lib/libosvrConnection.so" )

# Import target "osvr::osvrPluginKit" for configuration "Release"
set_property(TARGET osvr::osvrPluginKit APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(osvr::osvrPluginKit PROPERTIES
  IMPORTED_LINK_DEPENDENT_LIBRARIES_RELEASE "osvr::osvrPluginHost;osvr::osvrConnection;osvr::osvrCommon"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libosvrPluginKit.so"
  IMPORTED_SONAME_RELEASE "libosvrPluginKit.so"
  )

list(APPEND _IMPORT_CHECK_TARGETS osvr::osvrPluginKit )
list(APPEND _IMPORT_CHECK_FILES_FOR_osvr::osvrPluginKit "${_IMPORT_PREFIX}/lib/libosvrPluginKit.so" )

# Import target "osvr::osvrServer" for configuration "Release"
set_property(TARGET osvr::osvrServer APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(osvr::osvrServer PROPERTIES
  IMPORTED_LINK_DEPENDENT_LIBRARIES_RELEASE "osvr::osvrCommon;jsoncpp_lib"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libosvrServer.so"
  IMPORTED_SONAME_RELEASE "libosvrServer.so"
  )

list(APPEND _IMPORT_CHECK_TARGETS osvr::osvrServer )
list(APPEND _IMPORT_CHECK_FILES_FOR_osvr::osvrServer "${_IMPORT_PREFIX}/lib/libosvrServer.so" )

# Import target "osvr::osvrVRPNServer" for configuration "Release"
set_property(TARGET osvr::osvrVRPNServer APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(osvr::osvrVRPNServer PROPERTIES
  IMPORTED_LINK_DEPENDENT_LIBRARIES_RELEASE "osvr::osvrPluginHost"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libosvrVRPNServer.so"
  IMPORTED_SONAME_RELEASE "libosvrVRPNServer.so"
  )

list(APPEND _IMPORT_CHECK_TARGETS osvr::osvrVRPNServer )
list(APPEND _IMPORT_CHECK_FILES_FOR_osvr::osvrVRPNServer "${_IMPORT_PREFIX}/lib/libosvrVRPNServer.so" )

# Import target "osvr::osvrClient" for configuration "Release"
set_property(TARGET osvr::osvrClient APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(osvr::osvrClient PROPERTIES
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libosvrClient.so"
  IMPORTED_SONAME_RELEASE "libosvrClient.so"
  )

list(APPEND _IMPORT_CHECK_TARGETS osvr::osvrClient )
list(APPEND _IMPORT_CHECK_FILES_FOR_osvr::osvrClient "${_IMPORT_PREFIX}/lib/libosvrClient.so" )

# Import target "osvr::osvrClientKit" for configuration "Release"
set_property(TARGET osvr::osvrClientKit APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(osvr::osvrClientKit PROPERTIES
  IMPORTED_LINK_DEPENDENT_LIBRARIES_RELEASE "osvr::osvrClient;osvr::osvrCommon"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libosvrClientKit.so"
  IMPORTED_SONAME_RELEASE "libosvrClientKit.so"
  )

list(APPEND _IMPORT_CHECK_TARGETS osvr::osvrClientKit )
list(APPEND _IMPORT_CHECK_FILES_FOR_osvr::osvrClientKit "${_IMPORT_PREFIX}/lib/libosvrClientKit.so" )

# Import target "osvr::osvrAnalysisPluginKit" for configuration "Release"
set_property(TARGET osvr::osvrAnalysisPluginKit APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(osvr::osvrAnalysisPluginKit PROPERTIES
  IMPORTED_LINK_DEPENDENT_LIBRARIES_RELEASE "osvr::osvrPluginHost;osvr::osvrConnection;osvr::osvrCommon;osvr::osvrClient"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libosvrAnalysisPluginKit.so"
  IMPORTED_SONAME_RELEASE "libosvrAnalysisPluginKit.so"
  )

list(APPEND _IMPORT_CHECK_TARGETS osvr::osvrAnalysisPluginKit )
list(APPEND _IMPORT_CHECK_FILES_FOR_osvr::osvrAnalysisPluginKit "${_IMPORT_PREFIX}/lib/libosvrAnalysisPluginKit.so" )

# Import target "osvr::osvrJointClientKit" for configuration "Release"
set_property(TARGET osvr::osvrJointClientKit APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(osvr::osvrJointClientKit PROPERTIES
  IMPORTED_LINK_DEPENDENT_LIBRARIES_RELEASE "osvr::osvrClient;osvr::osvrCommon;osvr::osvrServer;jsoncpp_lib"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libosvrJointClientKit.so"
  IMPORTED_SONAME_RELEASE "libosvrJointClientKit.so"
  )

list(APPEND _IMPORT_CHECK_TARGETS osvr::osvrJointClientKit )
list(APPEND _IMPORT_CHECK_FILES_FOR_osvr::osvrJointClientKit "${_IMPORT_PREFIX}/lib/libosvrJointClientKit.so" )

# Commands beyond this point should not need to know the version.
set(CMAKE_IMPORT_FILE_VERSION)
