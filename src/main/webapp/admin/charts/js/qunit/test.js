/**
 * Created by qk on 18/3/7.
 */
QUnit.test( "hello test", function( assert ) {
    assert.ok( 1 == "1", "Passed!" );
});


// http://blog.csdn.net/boycycyzero/article/details/43916727
var X = function () {
    this.fire = function () {
        return $.ajax({ url: "../../../api/greetings/100",
            type : 'GET',
            async: false,
            error : function(xhr) {
                alert('error');
            },
            success : function(response) {
                return response.result;
            }
        });
    };
};

// create a function that counts down to `start()`
function createAsyncCounter(count) {
    count = count || 1; // count defaults to 1
    return function () { --count || QUnit.start(); };
}

// an async test that expects 2 assertions
QUnit.test("testing something ", 2, function(assert) {
    var countDown = createAsyncCounter(1), // the number of async calls in this test
        x = new X;

    // A `done` callback is the same as adding a `success` handler
    // in the ajax options. It's called after the "real" success handler.
    // I'm assuming here, that `fire()` returns the xhr object
    x.fire().done(function(data, status, jqXHR) {
        // assert.ok(data.ok);
        // assert.equal(data.value, "123");
    }).always(countDown); // call `countDown` regardless of success/error
});

