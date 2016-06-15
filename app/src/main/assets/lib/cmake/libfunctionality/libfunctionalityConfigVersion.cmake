# will always be the actual version one
# Wrapped in a macro because it's got a return before we want.
macro(_gcvf_version)
    # This is a basic version file for the Config-mode of find_package().
# It is used by write_basic_package_version_file() as input file for configure_file()
# to create a version-file which can be installed along a config.cmake file.
#
# The created file sets PACKAGE_VERSION_EXACT if the current version string and
# the requested version string are exactly the same and it sets
# PACKAGE_VERSION_COMPATIBLE if the current version is >= requested version.
# The variable CVF_VERSION must be set before calling configure_file().

set(PACKAGE_VERSION "0.1")

if("${PACKAGE_VERSION}" VERSION_LESS "${PACKAGE_FIND_VERSION}" )
  set(PACKAGE_VERSION_COMPATIBLE FALSE)
else()
  set(PACKAGE_VERSION_COMPATIBLE TRUE)
  if( "${PACKAGE_FIND_VERSION}" STREQUAL "${PACKAGE_VERSION}")
    set(PACKAGE_VERSION_EXACT TRUE)
  endif()
endif()

# if the installed or the using project don't have CMAKE_SIZEOF_VOID_P set, ignore it:
if("${CMAKE_SIZEOF_VOID_P}"  STREQUAL ""  OR "4" STREQUAL "")
   return()
endif()

# check that the installed version has the same 32/64bit-ness as the one which is currently searching:
if(NOT "${CMAKE_SIZEOF_VOID_P}"  STREQUAL  "4")
   math(EXPR installedBits "4 * 8")
   set(PACKAGE_VERSION "${PACKAGE_VERSION} (${installedBits}bit)")
   set(PACKAGE_VERSION_UNSUITABLE TRUE)
endif()

endmacro()
_gcvf_version()


if(NOT "${CMAKE_SYSTEM_NAME}" STREQUAL "Android")
   set(PACKAGE_VERSION "${PACKAGE_VERSION} (Android)")
   set(PACKAGE_VERSION_UNSUITABLE TRUE)
   return()
endif()

if(ANDROID AND NOT ("${ANDROID_ABI}" STREQUAL "armeabi"))
   set(PACKAGE_VERSION "${PACKAGE_VERSION} (Android armeabi)")
   set(PACKAGE_VERSION_UNSUITABLE TRUE)
   return()
endif()


if(NOT ("${CMAKE_C_COMPILER_ABI}" STREQUAL "") AND NOT ("ELF" STREQUAL ""))
    if(NOT "${CMAKE_C_COMPILER_ABI}" STREQUAL "ELF")
       set(PACKAGE_VERSION "${PACKAGE_VERSION} (ELF)")
       set(PACKAGE_VERSION_UNSUITABLE TRUE)
       return()
    endif()
endif()

if(NOT "${CMAKE_C_COMPILER_TARGET}" STREQUAL "")
   set(PACKAGE_VERSION "${PACKAGE_VERSION} ()")
   set(PACKAGE_VERSION_UNSUITABLE TRUE)
   return()
endif()
# todo anything for a C ABI beyond just "basic"?
