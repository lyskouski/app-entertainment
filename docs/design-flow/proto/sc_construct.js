// Apply image
var cont = {
    bm: null,
    color: ['silver', 'gray', 'black'],
    
    updatePrice: function () {
        var cost = 0;
        jQuery('#sc-plotter .sc-plotter-area').each(function () {
            cost += parseInt(jQuery(this).data('cost'), 10);
        });
        jQuery('#sc-price').html('<b>' + cost + '</b> USD');
    },
    
    tryColor: function (o, target, type, def) {
        if (o.hasClass('active')) {
            o.removeClass('active');
            cont.color[type] = def;
        } else {
            jQuery('#sc-item-list').find('.sc-block').removeClass('active');
            o.addClass('active');
            cont.color[type] = "url('" + o.find('img')[0].getAttribute('src').replace('_thumb', '') + "')";
        }
        cont.colorize(target);
    },
    
    colorize: function (target) {
        target.each(function () {
            $(this).find('img').each(function (i) {
                $(this).css('background', cont.color[i])
                   .css('background-size', '170px');
            });
        });
    },
    
    tryOn: function (o, target) {
        // Deactivate
        if (o.hasClass('active')) {
            target.html('');
            target.data('cost', 0);
            o.removeClass('active');
        // Change close
        } else {
            jQuery('#sc-item-list').find('.sc-block').removeClass('active');
            o.addClass('active');
            target.data('cost', o.data('cost'));
            target.data('src', o.find('img')[0].getAttribute('src'));
            
            target.html('');
            o.find('img').each(function (i) {
                var el = $('<img class="sc-image" src="./images/none.svg" />'),
                    mask = "url('" + this.getAttribute('src') + "') no-repeat";
                el.attr('style', "background: " + cont.color[i] + ";background-size: 170px;-webkit-mask: " + mask + ";");
                el.css('mask', mask);
                target.append(el);
            });
        }
    },
    
    dress: function () {
        var o = jQuery(this),
            target = jQuery('#' + cont.bm.data('target'));

        if (o.data('target')) {
            target = jQuery('#' + o.data('target'));
        }

        switch (cont.bm.data('step')) {
            case 0:
                cont.tryOn(o, target);
                break;
            case 1:
                cont.tryColor(o, target, 0, 'silver');
                break;
            case 2:
                cont.tryColor(o, target, 1, 'gray');
                break;
        }        
        
        cont.updatePrice();
    }
};

// Apply button inline list
jQuery('.sc-container-nav:eq(0) .sc-button').bind('click', function () {
    cont.bm = jQuery(this);
    cont.bm.addClass('sc-active');
    var list = jQuery('#sc-item-list'),
        target = jQuery('#' + cont.bm.data('target'));

    jQuery('.sc-navigation .sc-navigation-step:eq(' + cont.bm.data('step') + ')').addClass('sc-active');

    list.html(jQuery(cont.bm.find('.sc-list').html()).clone());
    list.find('.sc-block').bind('click', cont.dress);
    list.find('.sc-block').bind('dragend', cont.dress);
    if (target.length) {
        list.find('.sc-block img[src="' + target[0].getAttribute('src') + '"]').parent().addClass('active');
    }
});
jQuery('.sc-container-nav:eq(0) .sc-button:eq(2)').trigger('click');

// Switcher: change visibility for target blocks, clear defined list
jQuery('.sc-switch .sc-button').unbind('click').bind('click', function (event) {    // Set active
    jQuery(this).addClass('sc-active');
    // Cleanup
    jQuery('#sc-plotter .sc-plotter-area').data('cost', 0);
    jQuery('#sc-plotter .sc-plotter-area img').remove();
    cont.updatePrice();
    cont.color = ['silver', 'gray', 'black'];
    // Cleanup
    jQuery('.sc-container-nav:eq(0) .sc-button').removeClass('sc-active');
    jQuery(this).closest('.sc-switch').find('.sc-button').each(function () {
        jQuery('#' + jQuery(this).data('target')).addClass('sc-hidden');
    })
    // Apply target conditions
    var target = jQuery('#' + jQuery(this).data('target'));
    target.removeClass('sc-hidden');
    jQuery('#sc-item-list').html('');
    target.find('.sc-button:eq(0)').trigger('click');
    jQuery('#sc_plot_0').attr('src', jQuery(this).data('image'));
    // Stop propagation
    if (!event) {
        event = window.event;
    }
    event.cancelBubble = true;
    if (event.stopPropagation) {
        event.stopPropagation();
    }
});

// Trigger next button
jQuery('#sc_next').bind('click', function() {
    var obj = jQuery('#sp_list_w .sc-active:last').next();
    if (obj.length) {
        obj.trigger('click');
    } else {
        confirm('Ready to buy?');
    }
});

// Save URL with predefined items
jQuery('#sc_save').bind('click', function () {
    var item,
        isWomen = ~jQuery('#sc_plot_0').attr('src').indexOf('body-w'),
        prefix = isWomen ? 'w-' : 'm-',
        items = isWomen ? [prefix, ''] : ['', prefix];
        
    for (var i = 1; i < 6; i++) {
        item = jQuery('#sc_plot_' + i).data('src');
        if (item && item.split(prefix)[1]) {
            items.push(item.split(prefix)[1].slice(0, -4));
        } else {
            items.push('');
        }
    }
    
    window.location.hash = items.join(';');
    alert('URL adress has been changed in accordance. Use it to restore the state.');
});

// Restore state
if (window.location.hash) {
    var items = window.location.hash.slice(1).split(';'),
        isWonem = items[0].length;
    jQuery('.sc-switch .sc-button:eq(' + (isWonem ? 0 : 1) + ')').trigger('click');
    jQuery('#sp_list_' + (isWonem ? 'w' : 'm') + ' .sc-button').each(function (i, o) {
        if (items[i+2]) {
            jQuery(o).trigger('click');
            jQuery('#sc-item-list img[src*="' + items[0] + items[1] + items[i+2] + '"]').parent().trigger('click');
        }
    });
}