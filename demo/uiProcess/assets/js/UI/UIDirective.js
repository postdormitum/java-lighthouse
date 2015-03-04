(function() {
  'use strict';
  angular.module('app.ui.directives', []).directive('uiProcess', [
    function() {
      var compilationFunction;
      compilationFunction = function(templateElement, templateAttributes, transclude) {
        var height, node, preLink, svg;
        if (templateElement.length === 1) {
          node = templateElement[0];
          height = node.getAttribute('data-process-height') || '90';
          svg = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
          svg.setAttribute('xmlns', 'http://www.w3.org/2000/svg');
          svg.setAttribute('version', 1.1);
          svg.setAttribute('width', '100%');
          svg.setAttribute('height', height);
          svg.setAttribute('data-process-model', node.getAttribute('data-process-model'));
          node.parentNode.replaceChild(svg, node);
          return {
            pre: preLink = function(scope, instanceElement, instanceAttributes, controller) {
              var getFillColor, nodedata;
              nodedata = svg.getAttribute('data-process-model');
              getFillColor = function(type, rollback) {
                var result;
                if (rollback == null) {
                  rollback = 0;
                }
                result = '#7b7b7b';
                if (rollback > 0) {
                  result = 'red';
                } else if (type === 'ok') {
                  result = '#669e41';
                } else if (type === 'wait') {
                  result = '#5b9bd5';
                }
                return result;
              };
              return scope.$watch(nodedata, function(newValue, oldValue) {
                var childCount, count, gap, getArrow, getCircle, isChild, line, loopProcessData;
                count = 0;
                isChild = false;
                childCount = 0;
                gap = newValue.gap || 200;
                line = null;
                getArrow = function(type) {
                  var path, rollback;
                  rollback = 0;
                  path = document.createElementNS('http://www.w3.org/2000/svg', 'path');
                  switch (type) {
                    case 2:
                      path.setAttribute('d', 'm' + (73 + (count - 1) * gap) + ',' + (height / 2 - 3) + 'h' + ((gap - 74) / 2 + 4) + 'v' + (childCount - 2) * 90 + 'h' + ((gap - 74) / 2 - 4) + 'v-6l8,10l-8,10v-6h-' + ((gap - 74) / 2 + 4) + 'v-' + (childCount - 2) * 90 + 'h-' + ((gap - 74) / 2 - 4) + 'z');
                      break;
                    case 3:
                      path.setAttribute('d', 'm' + (73 + count * gap) + ',' + (height / 2 - 3 + (childCount - 2) * 90) + 'h' + ((gap - 74) / 2 - 4) + 'v-' + (childCount - 2) * 90 + 'h' + ((gap - 74) / 2 + 4) + 'v-6l8,10l-8,10v-6h-' + ((gap - 74) / 2 - 4) + 'v' + (childCount - 2) * 90 + 'h-' + ((gap - 74) / 2 + 4) + 'z');
                      break;
                    case 4:
                      path.setAttribute('d', 'm' + (7 + count * gap) + ',' + (height / 2 - 3 + (childCount - 2) * 90) + 'h-' + ((gap - 74) / 2 + 4) + 'v-' + (childCount - 2) * 90 + 'h-' + ((gap - 74) / 2 - 4) + 'v-6l-8,10l8,10v-6h' + ((gap - 74) / 2 - 12) + 'v' + (childCount - 2) * 90 + 'h' + ((gap - 74) / 2 + 12) + 'z');
                      rollback = 1;
                      break;
                    case 5:
                      path.setAttribute('d', 'm' + (7 + count * gap) + ',' + (height / 2 - 3 + (childCount > 2 && isChild ? childCount - 2 : 0) * 90) + 'h-' + (gap - 74) + 'v-6l-8,10l8,10v-6h' + (gap - 74) + 'z');
                      rollback = 1;
                      break;
                    default:
                      path.setAttribute('d', 'm' + (73 + count * gap) + ',' + (height / 2 - 3 + (childCount > 2 && isChild ? childCount - 2 : 0) * 90) + 'h' + (gap - 60 - 6 - 8) + 'v-6l8,10l-8,10v-6h-' + (gap - 60 - 6 - 8) + 'z');
                  }
                  path.setAttribute('stroke-opacity', 'null');
                  path.setAttribute('stroke-width', 0);
                  path.setAttribute('fill', getFillColor(type, rollback));
                  return path;
                };
                getCircle = function(data, index, length) {
                  var circle;
                  circle = document.createElementNS('http://www.w3.org/2000/svg', 'circle');
                  circle.setAttribute('cx', 40 + count * gap);
                  circle.setAttribute('cy', height / 2 + (childCount > 2 && isChild ? childCount - 2 : 0) * 90);
                  circle.setAttribute('r', 30);
                  circle.setAttribute('stroke-width', 6);
                  if (data.type === 'ok') {
                    if (count === 0) {
                      circle.setAttribute('stroke', '#92c570');
                    } else {
                      circle.setAttribute('stroke', '#669e41');
                    }
                  } else if (data.type === 'wait') {
                    circle.setAttribute('stroke', '#5b9bd5');
                  } else {
                    if (isChild || index + 1 !== length) {
                      circle.setAttribute('stroke', '#7b7b7b');
                    } else {
                      circle.setAttribute('stroke', '#515151');
                    }
                  }
                  circle.setAttribute('fill', getFillColor(data.type));
                  if (data.message !== '') {
                    circle.onmousedown = function() {
                      return alert(data.message);
                    };
                  }
                  return circle;
                };
                loopProcessData = function(newValue) {
                  var value;
                  value = newValue.nodes ? newValue.nodes : newValue;
                  return angular.forEach(value, function(data, index) {
                    var text;
                    if (angular.isArray(data)) {
                      isChild = true;
                      childCount++;
                      index += loopProcessData(data);
                      count--;
                      isChild = false;
                    } else {
                      data.rollback = data.rollback || 0;
                      if (childCount > 2 && index === 0) {
                        count -= value.length;
                        svg.appendChild(getArrow(data.rollback > 0 ? 4 : 2));
                      }
                      if (line) {
                        if (data.rollback > 0 && !(childCount === 3 && index === 0)) {
                          svg.appendChild(getArrow(5));
                        } else {
                          svg.appendChild(line);
                        }
                        line = null;
                      }
                      svg.appendChild(getCircle(data, index, value.length));
                      text = document.createElementNS('http://www.w3.org/2000/svg', 'text');
                      text.setAttribute('x', 26 + count * gap);
                      text.setAttribute('y', height / 2 + 5 + (childCount > 2 && isChild ? childCount - 2 : 0) * 90);
                      text.setAttribute('style', 'font-family:Verdana;font-size:14;fill:white');
                      text.textContent = data.label;
                      svg.appendChild(text);
                      if (isChild || index + 1 !== value.length) {
                        if (childCount > 2 && index + 1 === value.length) {
                          svg.appendChild(getArrow(3));
                        } else {
                          line = getArrow(1);
                        }
                      }
                    }
                    return count++;
                  });
                };
                loopProcessData(newValue);
                if (childCount > 2 && height < (childCount - 1) * 90) {
                  return svg.setAttribute('height', (childCount - 1) * 90);
                }
              });
            }
          };
        }
      };
      return {
        compile: compilationFunction,
        replace: true
      };
    }
  ]);

}).call(this);
