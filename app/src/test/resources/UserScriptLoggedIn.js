if (!window.jQuery) { document.getElementById('noscript-warning').style.display = 'block'; }
$('#noscript-warning').hide();
try {
    // Set up tag lists for client-side image spoilering/hiding
    window.booru.filterID = 100073;
    window.booru.filterCanEdit = false;
    window.booru.hiddenTagList = [61060,89379,20417,86723,94478];
    window.booru.spoileredTagList = [41133,41161,42773,114937,173118,173119,173120,173121,173122,173123,173124];
    window.booru.hiddenFilter = window.booru.parseSearch("");
    window.booru.spoileredFilter = window.booru.parseSearch("");
    window.booru.ignoredTagList = [];
    window.booru.watchedTagList = [220545];
    window.booru.userID = 365446;
    window.booru.userSlug = "TestUserName = Test";
    window.booru.userName = "TestUserName = Test";
    window.booru.userAvatar = "//derpicdn.net/avatars/examplePath.png";
    window.booru.spoilerType = "hover";
    window.booru._hidden_tag = "//derpicdn.net/assets/tagblocked-a6790e31ad2d0ec2fdd400aab8ba2eb7177c0489c12525bfda9ef42be933e662.svg";
    window.booru._interactions = [{"id":1,"interaction_type":"faved","value":null,"user_id":1,"image_id":10005},{"id":1,"interaction_type":"voted","value":"up","user_id":1,"image_id":10005}];
    window.booru.enable_browser_ponies = false;
    // CDN root
    window.booru._cdnHost = "//derpicdn.net"
    // Image root
    window.booru._imgHost = "//derpicdn.net/img"
    // Fetch tag metadata and set up filtering
    window.booru.initializeFilters();
    // Show buttons on this user's owned objects
    window.booru.showOwnedObjects();
    // Mouseover and other events
    window.booru.images.bindEvents();
} catch(e) {
    // Bubble up to error console for users
    throw e;
}