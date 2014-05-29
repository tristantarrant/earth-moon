function widgetBaseUrl(widgetBody, widgetBox) {
    return 'app/celestialbody/' + widgetBody + '/' + widgetBox;
}

function refreshWidgets(widgetBody, widgetBox) {
    var box = $(widgetBody).getElement("div." + widgetBox);
    new Request.JSON({url: widgetBaseUrl(widgetBody, widgetBox) + '/widgets', method: 'get',
        onSuccess: function(widgets) {
            box.empty();
            for(var widget in widgets) {
                var w = new Element('div', {
                    'class': 'widget',
                    'style': 'background-color: ' + widgets[widget]
                });
                box.adopt(w);
            }
        },
        onFailure: function(xhr) {
            box.empty();
            box.addClass("error");
        }
    }).send();
}

function celestialBodyBox(id, box) {
    $(id).getElement("div." + box).addEvent('dblclick', function() {
        $('widgetBody').value = id;
        $('widgetBox').value = box;
        $('widgetForm').show();
        $('widgetForm').position({relativeTo: $(id)});
    });
    refreshWidgets.periodical(2000, this, [id, box]);
}

function celestialBody(id) {
    new Drag.Move($(id), {

        container : $('container'),

        droppables : $$('.droppable'),

        onEnter : function(element, droppable) {
        },

        onLeave : function(element, droppable) {
        },

        onDrop : function(element, droppable) {
        }

    });
    celestialBodyBox(id, 'local');
    celestialBodyBox(id, 'global');
}

function initialize() {
    celestialBody('earth');
    celestialBody('moon');
    new MooRainbow('widgetColor', {
        imgPath: 'img/',
        zIndex: 22,
        onChange: function(color) {
            this.element.value = color.hex;
        }
    });

    $('widgetAdd').addEvent('click', function() {
        var widgetBox = $('widgetBox').value;
        var widgetColor = $('widgetColor').value;
        var widgetName = widgetColor.substring(1);
        var widgetBody = $('widgetBody').value;
        new Request({url: widgetBaseUrl(widgetBody, widgetBox) + '/widget/' + widgetName, method: 'post'}).send('color=' + widgetColor);
        $('widgetForm').hide();
        return false;
    });
    $('widgetCancel').addEvent('click', function() {
        $('widgetForm').hide();
        return false;
    });
}

window.addEvent('domready', function() {
    initialize();
});
