;(function(window) {

  var svgSprite = '<svg>' +
    '' +
    '<symbol id="icon-jiantou" viewBox="0 0 1024 1024">' +
    '' +
    '<path d="M682.26229 954.784592 568.740794 954.784592 341.736687 510.976694 568.740794 67.167772 682.26229 67.167772 455.256136 510.976694 682.26229 954.784592 682.26229 954.784592zM682.26229 954.784592"  ></path>' +
    '' +
    '</symbol>' +
    '' +
    '<symbol id="icon-shangyiye" viewBox="0 0 1024 1024">' +
    '' +
    '<path d="M206.868687 511.930182l270.828606-270.840242-0.085333-0.080162 16.717576-16.718869c12.889212-12.889212 33.782949-12.889212 46.661818 0 12.889212 12.880162 12.889212 33.772606 0 46.663111L299.929859 512.020687l242.241939 242.241939c12.88404 12.878869 12.88404 33.773899 0 46.661818-12.887919 12.88404-33.777778 12.88404-46.661818 0l-26.989899-26.989899 0.182303-0.166788L206.868687 511.930182 206.868687 511.930182zM470.833131 511.930182l270.833778-270.840242-0.08404-0.080162 16.716283-16.718869c12.889212-12.889212 33.782949-12.889212 46.661818 0 12.889212 12.880162 12.889212 33.772606 0 46.663111L563.899475 512.020687 806.141414 754.262626c12.88404 12.878869 12.88404 33.773899 0 46.661818-12.887919 12.88404-33.782949 12.88404-46.660525 0l-26.995071-26.989899 0.186182-0.166788L470.833131 511.930182 470.833131 511.930182zM470.833131 511.930182"  ></path>' +
    '' +
    '</symbol>' +
    '' +
    '<symbol id="icon-xiayiye" viewBox="0 0 1024 1024">' +
    '' +
    '<path d="M817.131 512.070l-287.461 287.639c-12.889 12.889-33.783 12.889-46.662 0-12.888-12.88-12.889-33.773 0-46.663l241.061-241.067-242.242-242.242c-12.884-12.879-12.884-33.774 0-46.662 12.888-12.884 33.778-12.884 46.662 0l26.99 26.99-0.182 0.167 261.834 261.838zM553.167 512.070l-287.466 287.639c-12.889 12.889-33.783 12.889-46.662 0-12.888-12.88-12.889-33.773 0-46.663l241.061-241.067-242.242-242.242c-12.884-12.879-12.884-33.774 0-46.662 12.888-12.884 33.783-12.884 46.661 0l26.995 26.99-0.186 0.168 261.84 261.838zM553.167 512.070z"  ></path>' +
    '' +
    '</symbol>' +
    '' +
    '<symbol id="icon-jiantou1" viewBox="0 0 1024 1024">' +
    '' +
    '<path d="M 341.738 69.215 h 113.52 l 227.004 443.809 l -227.004 443.809 h -113.52 l 227.006 -443.809 l -227.006 -443.808 Z M 341.738 69.215 Z"  ></path>' +
    '' +
    '</symbol>' +
    '' +
    '</svg>'
  var script = function() {
    var scripts = document.getElementsByTagName('script')
    return scripts[scripts.length - 1]
  }()
  var shouldInjectCss = script.getAttribute("data-injectcss")

  /**
   * document ready
   */
  var ready = function(fn) {
    if (document.addEventListener) {
      if (~["complete", "loaded", "interactive"].indexOf(document.readyState)) {
        setTimeout(fn, 0)
      } else {
        var loadFn = function() {
          document.removeEventListener("DOMContentLoaded", loadFn, false)
          fn()
        }
        document.addEventListener("DOMContentLoaded", loadFn, false)
      }
    } else if (document.attachEvent) {
      IEContentLoaded(window, fn)
    }

    function IEContentLoaded(w, fn) {
      var d = w.document,
        done = false,
        // only fire once
        init = function() {
          if (!done) {
            done = true
            fn()
          }
        }
        // polling for no errors
      var polling = function() {
        try {
          // throws errors until after ondocumentready
          d.documentElement.doScroll('left')
        } catch (e) {
          setTimeout(polling, 50)
          return
        }
        // no errors, fire

        init()
      };

      polling()
        // trying to always fire before onload
      d.onreadystatechange = function() {
        if (d.readyState == 'complete') {
          d.onreadystatechange = null
          init()
        }
      }
    }
  }

  /**
   * Insert el before target
   *
   * @param {Element} el
   * @param {Element} target
   */

  var before = function(el, target) {
    target.parentNode.insertBefore(el, target)
  }

  /**
   * Prepend el to target
   *
   * @param {Element} el
   * @param {Element} target
   */

  var prepend = function(el, target) {
    if (target.firstChild) {
      before(el, target.firstChild)
    } else {
      target.appendChild(el)
    }
  }

  function appendSvg() {
    var div, svg

    div = document.createElement('div')
    div.innerHTML = svgSprite
    svgSprite = null
    svg = div.getElementsByTagName('svg')[0]
    if (svg) {
      svg.setAttribute('aria-hidden', 'true')
      svg.style.position = 'absolute'
      svg.style.width = 0
      svg.style.height = 0
      svg.style.overflow = 'hidden'
      prepend(svg, document.body)
    }
  }

  if (shouldInjectCss && !window.__iconfont__svg__cssinject__) {
    window.__iconfont__svg__cssinject__ = true
    try {
      document.write("<style>.svgfont {display: inline-block;width: 1em;height: 1em;fill: currentColor;vertical-align: -0.1em;font-size:16px;}</style>");
    } catch (e) {
      console && console.log(e)
    }
  }

  ready(appendSvg)


})(window)