import LeaderLine from 'leader-line-new';

window.leaderLines = {};

window.leaderLineAnchors = {};
window.leaderLineAnchors.pointAnchor = LeaderLine.pointAnchor;
window.leaderLineAnchors.element = (el, options) => {
    return el;
}

window.leaderLine = (id, start, end, options) => {
    const line = new LeaderLine(start, end, options);
    // save the line for later use
    leaderLines[id] = line;
    // hack around deferred Vaadin rendering
    setTimeout(() => {
        line.position();
    }, 0);
}
