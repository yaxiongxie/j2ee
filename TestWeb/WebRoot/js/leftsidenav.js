$(function() {

    $('#side-menu').metisMenu();

});

//Loads the correct sidebar on window load,
//collapses the sidebar on window resize.
// Sets the min-height of #page-wrapper to window size
$(function() {
    $(window).bind("load resize", function() {
        var topOffset = 50;
        var width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
        if (width < 768) {
            $('div.navbar-collapse').addClass('collapse');
            topOffset = 100; // 2-row-menu
        } else {
            $('div.navbar-collapse').removeClass('collapse');
        }

        var height = ((this.window.innerHeight > 0) ? this.window.innerHeight : this.screen.height) - 1;
        height = height - topOffset;
        if (height < 1) height = 1;
        if (height > topOffset) {
            $("#page-wrapper").css("min-height", (height) + "px");
        }
    });

    var url = window.location;
    var element = $('ul.nav a').filter(function() {
        return this.href == url;
    }).addClass('active').parent().parent().addClass('in').parent();
    if (element.is('li')) {
        element.addClass('active');
    }
});

/**
 * metis js
 */

!function(a,b){if("function"==typeof define&&define.amd)define(["jquery"],b);else if("undefined"!=typeof exports)b(require("jquery"));else{var c={exports:{}};b(a.jquery),a.metisMenu=c.exports}}(this,function(a){"use strict";function b(a){return a&&a.__esModule?a:{"default":a}}function c(a,b){if(!(a instanceof b))throw new TypeError("Cannot call a class as a function")}var d=(b(a),"function"==typeof Symbol&&"symbol"==typeof Symbol.iterator?function(a){return typeof a}:function(a){return a&&"function"==typeof Symbol&&a.constructor===Symbol?"symbol":typeof a}),e=function(){function a(a,b){for(var c=0;c<b.length;c++){var d=b[c];d.enumerable=d.enumerable||!1,d.configurable=!0,"value"in d&&(d.writable=!0),Object.defineProperty(a,d.key,d)}}return function(b,c,d){return c&&a(b.prototype,c),d&&a(b,d),b}}();(function(a){function b(){return{bindType:q.end,delegateType:q.end,handle:function(b){return a(b.target).is(this)?b.handleObj.handler.apply(this,arguments):void 0}}}function f(){if(window.QUnit)return!1;var a=document.createElement("mm");for(var b in r)if(void 0!==a.style[b])return{end:r[b]};return!1}function g(b){var c=this,d=!1;a(this).one(s.TRANSITION_END,function(){d=!0}),setTimeout(function(){d||s.triggerTransitionEnd(c)},b)}function h(){q=f(),s.supportsTransitionEnd()&&(a.event.special[s.TRANSITION_END]=b())}var i="metisMenu",j="metisMenu",k="."+j,l=".data-api",m=a.fn[i],n=350,o={toggle:!0,doubleTapToGo:!1,preventDefault:!0,activeClass:"active",collapseClass:"collapse",collapseInClass:"in",collapsingClass:"collapsing"},p={SHOW:"show"+k,SHOWN:"shown"+k,HIDE:"hide"+k,HIDDEN:"hidden"+k,CLICK_DATA_API:"click"+k+l},q=!1,r={WebkitTransition:"webkitTransitionEnd",MozTransition:"transitionend",OTransition:"oTransitionEnd otransitionend",transition:"transitionend"},s={TRANSITION_END:"mmTransitionEnd",triggerTransitionEnd:function(b){a(b).trigger(q.end)},supportsTransitionEnd:function(){return Boolean(q)}};h();var t=function(){function b(a,d){c(this,b),this._element=a,this._config=this._getConfig(d),this._transitioning=null,this.init()}return e(b,[{key:"init",value:function(){var b=this;a(this._element).find("li."+this._config.activeClass).has("ul").children("ul").attr("aria-expanded",!0).addClass(this._config.collapseClass+" "+this._config.collapseInClass),a(this._element).find("li").not("."+this._config.activeClass).has("ul").children("ul").attr("aria-expanded",!1).addClass(this._config.collapseClass),this._config.doubleTapToGo&&a(this._element).find("li."+this._config.activeClass).has("ul").children("a").addClass("doubleTapToGo"),a(this._element).find("li").has("ul").children("a").on(p.CLICK_DATA_API,function(c){var d=a(this),e=d.parent("li"),f=e.children("ul");return b._config.preventDefault&&c.preventDefault(),"true"!==d.attr("aria-disabled")?(e.hasClass(b._config.activeClass)&&!b._config.doubleTapToGo?(d.attr("aria-expanded",!1),b._hide(f)):(b._show(f),d.attr("aria-expanded",!0)),b._config.onTransitionStart&&b._config.onTransitionStart(c),b._config.doubleTapToGo&&b._doubleTapToGo(d)&&"#"!==d.attr("href")&&""!==d.attr("href")?(c.stopPropagation(),void(document.location=d.attr("href"))):void 0):void 0})}},{key:"_show",value:function(b){if(!this._transitioning&&!a(b).hasClass(this._config.collapsingClass)){var c=this,d=a(b),e=a.Event(p.SHOW);if(d.trigger(e),!e.isDefaultPrevented()){d.parent("li").addClass(this._config.activeClass),this._config.toggle&&this._hide(d.parent("li").siblings().children("ul."+this._config.collapseInClass).attr("aria-expanded",!1)),d.removeClass(this._config.collapseClass).addClass(this._config.collapsingClass).height(0),this.setTransitioning(!0);var f=function(){d.removeClass(c._config.collapsingClass).addClass(c._config.collapseClass+" "+c._config.collapseInClass).height("").attr("aria-expanded",!0),c.setTransitioning(!1),d.trigger(p.SHOWN)};if(!s.supportsTransitionEnd())return void f();d.height(d[0].scrollHeight).one(s.TRANSITION_END,f),g(n)}}}},{key:"_hide",value:function(b){if(!this._transitioning&&a(b).hasClass(this._config.collapseInClass)){var c=this,d=a(b),e=a.Event(p.HIDE);if(d.trigger(e),!e.isDefaultPrevented()){d.parent("li").removeClass(this._config.activeClass),d.height(d.height())[0].offsetHeight,d.addClass(this._config.collapsingClass).removeClass(this._config.collapseClass).removeClass(this._config.collapseInClass),this.setTransitioning(!0);var f=function(){c._transitioning&&c._config.onTransitionEnd&&c._config.onTransitionEnd(),c.setTransitioning(!1),d.trigger(p.HIDDEN),d.removeClass(c._config.collapsingClass).addClass(c._config.collapseClass).attr("aria-expanded",!1)};if(!s.supportsTransitionEnd())return void f();0==d.height()||"none"==d.css("display")?f():d.height(0).one(s.TRANSITION_END,f),g(n)}}}},{key:"_doubleTapToGo",value:function(b){return b.hasClass("doubleTapToGo")?(b.removeClass("doubleTapToGo"),!0):b.parent().children("ul").length?(a(this._element).find(".doubleTapToGo").removeClass("doubleTapToGo"),b.addClass("doubleTapToGo"),!1):void 0}},{key:"setTransitioning",value:function(a){this._transitioning=a}},{key:"_getConfig",value:function(b){return b=a.extend({},o,b)}}],[{key:"_jQueryInterface",value:function(c){return this.each(function(){var e=a(this),f=e.data(j),g=a.extend({},o,e.data(),"object"===("undefined"==typeof c?"undefined":d(c))&&c);if(f||(f=new b(this,g),e.data(j,f)),"string"==typeof c){if(void 0===f[c])throw new Error('No method named "'+c+'"');f[c]()}})}}]),b}();return a.fn[i]=t._jQueryInterface,a.fn[i].Constructor=t,a.fn[i].noConflict=function(){return a.fn[i]=m,t._jQueryInterface},t})(jQuery)});
//# sourceMappingURL=metisMenu.js.map

