#!/bin/bash
# init
function pause(){
   read -p "$*"
}

function create_manifest_text(){
   printf "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<manifest package=\"$1\"/>"
}

function create_fragment_text(){
   printf "@AndroidEntryPoint\nclass ${1}Fragment : BaseFragment(R.layout.fragment_) {\n\n}"
}

function create_viewModel_text(){
  printf "@HiltViewModel\nclass ${1}ViewModel @Inject constructor(): BaseViewModel<${1}ViewModel.ViewState>(ViewState()) {\n\n}"
}

# call it
echo "*****************************************"
echo "* MVVM Feature structure folder creator *"
echo "*****************************************"
echo "1. Enter feature name"
read folder_name
echo "2. Enter package name"
read package_name
echo "3. Enter prefix class name"
read feature_name
echo ""
echo "Creating feature structure into: folder_name ...";
mkdir -p "features"
cd "features"
mkdir -p "$folder_name"
cd "$folder_name"
package_name_format=${package_name//.//}
mkdir -p "src/main/java/$package_name_format"
echo "Creating build.gradle ...";
echo "" > build.gradle
cd "src/main"
echo "Creating AndroidManifest.xml ...";
create_manifest_text $package_name > AndroidManifest.xml
echo "Creating res folder ..."
mkdir -p "res/layout"
mkdir -p "res/values"
mkdir -p "res/values-es"
cd "java"
cd "$package_name_format"
echo "Creating data, di, domain, presentation folder ...";
mkdir "data"
mkdir "di"
mkdir "domain"
mkdir "presentation"
echo "Creating fragment and viewmodel classes into presentation ...";
cd "presentation"
create_fragment_text $feature_name > "${feature_name}Fragment.kt"
create_viewModel_text $feature_name > "${feature_name}ViewModel.kt"
pause 'Success! Press any key to exit'
# rest of the script
