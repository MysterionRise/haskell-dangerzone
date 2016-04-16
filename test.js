(function() {
  var elems = document.getElementsByTagName("a"),
   i;
for (i in elems) {
   if (elems[i].href.indexOf("/rajonchik37") > 0) {
      elems[i].innerHTML = "Лягуха";
   }
}
})();
